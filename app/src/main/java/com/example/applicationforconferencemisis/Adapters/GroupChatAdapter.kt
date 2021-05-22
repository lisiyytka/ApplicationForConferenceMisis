package com.example.applicationforconferencemisis.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_GROUP_MESSAGE
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Models.Message
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.asTime
import com.example.applicationforconferencemisis.makeToast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.getValue

class GroupChatAdapter(private val helper: SQLiteHelper) :
    RecyclerView.Adapter<GroupChatAdapter.GroupChatHolder>() {

    private var mListMessagesCache = emptyList<Message>()

    class GroupChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        val blocUserMessage: ConstraintLayout = view.findViewById(R.id.bloc_user_message)
        val myMsg: TextView = view.findViewById(R.id.my_message)
        val myMsgTime: TextView = view.findViewById(R.id.my_time)

        val blocReceivedMessage: ConstraintLayout = view.findViewById(R.id.bloc_received_message)
        val msg: TextView = view.findViewById(R.id.users_message)
        val msgTime: TextView = view.findViewById(R.id.time)
        val userName: TextView = view.findViewById(R.id.users_name)
        val userImg: CardView = view.findViewById(R.id.img_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_chat, parent, false)
        return GroupChatHolder(view)
    }

    override fun onBindViewHolder(holder: GroupChatHolder, position: Int) {

        if (mListMessagesCache[position].fromUser == helper.getUser().username) {
            holder.blocUserMessage.visibility = View.VISIBLE
            holder.blocReceivedMessage.visibility = View.GONE
            holder.myMsg.text = mListMessagesCache[position].text
            holder.myMsgTime.text = mListMessagesCache[position].date.toString().asTime()
        } else {
            holder.blocUserMessage.visibility = View.GONE
            holder.blocReceivedMessage.visibility = View.VISIBLE
            holder.msg.text = mListMessagesCache[position].text
            holder.msgTime.text = mListMessagesCache[position].date.toString().asTime()
            holder.userName.text = mListMessagesCache[position].fromUser
            holder.userImg.visibility = View.VISIBLE
            REF_DATABASE_ROOT.child(NODE_GROUP_MESSAGE).addListenerForSingleValueEvent(
                AppValueEventListener {
                    var list = arrayListOf<Message>()
                    for (i in it.children) {
                        list.add(i.getValue(Message::class.java)!!)
                    }

                    if (list[position] == list[list.size-1]){
                        holder.blocUserMessage.visibility = View.GONE
                        holder.blocReceivedMessage.visibility = View.VISIBLE
                        holder.msg.text = mListMessagesCache[position].text
                        holder.msgTime.text = mListMessagesCache[position].date.toString().asTime()
                        holder.userName.text = mListMessagesCache[position].fromUser
                        holder.userImg.visibility = View.VISIBLE
                    }else if (list[position].fromUser == list[position+1].fromUser &&
                        list[position+1].fromUser != helper.getUser().username) {
                        holder.blocUserMessage.visibility = View.GONE
                        holder.blocReceivedMessage.visibility = View.VISIBLE
                        holder.msg.text = mListMessagesCache[position].text
                        holder.msgTime.text = mListMessagesCache[position].date.toString().asTime()
                        holder.userName.text = mListMessagesCache[position].fromUser
                        holder.userImg.visibility = View.INVISIBLE
                    }
                }
            )
        }
    }

    override fun getItemCount(): Int = mListMessagesCache.size

    fun setList(list: List<Message>) {
        mListMessagesCache = list
        notifyDataSetChanged()
    }
}