package com.example.applicationforconferencemisis.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Activities.fragmentName
import com.example.applicationforconferencemisis.Activities.lastBtnId
import com.example.applicationforconferencemisis.Activities.lastFragment
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_MESSAGES
import com.example.applicationforconferencemisis.Data.Firebase.NODE_USERS
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Models.Contacts
import com.example.applicationforconferencemisis.Data.Models.Message
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.asTime
import com.example.applicationforconferencemisis.replaceFragment

class MessagesFragment: Fragment() {

    lateinit var adapter: RecyclerView.Adapter<MyViewHolder>
    lateinit var messagesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onStart(){
        super.onStart()
        val helper = SQLiteHelper(context!!)
        initRecyclerView(helper.getContacts())
    }

    private fun initRecyclerView(contacts: List<Contacts>){
        messagesRecyclerView = view?.findViewById(R.id.messagesRecyclerView)!!
        messagesRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = object : RecyclerView.Adapter<MyViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.messages_rv_component, parent, false)
                return MyViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                val helper = SQLiteHelper(context!!)
                REF_DATABASE_ROOT.child(NODE_USERS).child(contacts[position].usersName).addListenerForSingleValueEvent(
                    AppValueEventListener{

                        val user = it.getValue(User::class.java)
                        if (user != null){
                            holder.usersName.text = user.username
//                            holder.lastMessage.text = "asd"
                            REF_DATABASE_ROOT.child(NODE_MESSAGES).child(helper.getUser().username).child(contacts[position].usersName).addListenerForSingleValueEvent(
                                AppValueEventListener{
                                    val a = it.children.last().getValue(Message::class.java)
                                    holder.lastMessage.text=a!!.text
                                    holder.time.text = a.date.toString().asTime()
                                }
                            )
                            holder.numberOfUnreadMsg.text = position.toString()
                            holder.itemView.setOnClickListener {
                                lastFragment = MessagesFragment()
                                lastBtnId = R.id.messages_btn
                                fragmentName!!.text = user.username
                                replaceFragment(SingleChatFragment(user.username))
                            }
                        }
                    }
                )

            }

            override fun getItemCount() = contacts.size
        }
        messagesRecyclerView.adapter = adapter
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        var usersImg: TextView? = null
        var usersName: TextView = itemView.findViewById(R.id.users_name)
        var lastMessage: TextView = itemView.findViewById(R.id.last_message)
        var time: TextView = itemView.findViewById(R.id.time)
        var numberOfUnreadMsg: TextView = itemView.findViewById(R.id.number_of_unread_msg)
    }

    private fun getUserFromContacts(){

    }
}