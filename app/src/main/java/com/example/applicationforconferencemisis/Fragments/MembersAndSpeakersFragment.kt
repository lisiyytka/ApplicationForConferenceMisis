package com.example.applicationforconferencemisis.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Activities.fragmentName
import com.example.applicationforconferencemisis.Activities.lastBtnId
import com.example.applicationforconferencemisis.Activities.lastFragment
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_USERS
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Firebase.addNewDialog
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.replaceFragment
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference

class MembersAndSpeakersFragment : Fragment() {

    lateinit var membersRecyclerView: RecyclerView
    lateinit var mRefMembers: DatabaseReference
    private lateinit var mAdapter: FirebaseRecyclerAdapter<User, MembersHolder>
    private lateinit var mRefUsersListener:AppValueEventListener
    private  var mapListeners = hashMapOf<DatabaseReference,AppValueEventListener>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_members_speakers, container, false)
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        membersRecyclerView = view!!.findViewById(R.id.members_recycler_view)
        membersRecyclerView.layoutManager = LinearLayoutManager(context)
        mRefMembers = REF_DATABASE_ROOT.child(NODE_USERS)

        val option = FirebaseRecyclerOptions.Builder<User>()
            .setQuery(mRefMembers, User::class.java)
            .build()

        mAdapter = object : FirebaseRecyclerAdapter<User, MembersHolder>(option){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.members_speakers_component, parent, false)
                return MembersHolder(view)
            }

            override fun onBindViewHolder(holder: MembersHolder, position: Int, model: User) {
                val localDB = SQLiteHelper(context!!)
                mRefUsersListener = AppValueEventListener {

                    holder.userName.text = model.username
                    holder.sendMessageButton.setOnClickListener {
                        addNewDialog(model.username, context!!)
                        localDB.insertContactsToContacts(model.username)
                        lastFragment = MembersAndSpeakersFragment()
                        lastBtnId = R.id.members_btn
                        fragmentName!!.text = model.username
                        replaceFragment(SingleChatFragment(model.username))
                    }
                }
                mRefMembers.addListenerForSingleValueEvent(mRefUsersListener)
                mapListeners[mRefMembers] = mRefUsersListener
            }
        }
        membersRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    class MembersHolder (view: View): RecyclerView.ViewHolder(view){
        var userName: TextView = itemView.findViewById(R.id.users_name)
        var sendMessageButton: ImageView = itemView.findViewById(R.id.send_msg_btn)
//        var status: TextView = itemView.findViewById(R.id.users_status)
    }
}