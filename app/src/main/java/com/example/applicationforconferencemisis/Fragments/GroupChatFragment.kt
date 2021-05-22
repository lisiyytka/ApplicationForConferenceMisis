package com.example.applicationforconferencemisis.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Adapters.GroupChatAdapter
import com.example.applicationforconferencemisis.Adapters.SingleChatAdapter
import com.example.applicationforconferencemisis.Data.Firebase.*
import com.example.applicationforconferencemisis.Data.Models.Message
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.makeToast
import com.google.firebase.database.DatabaseReference

class GroupChatFragment() : Fragment() {

    lateinit var mAdapter: GroupChatAdapter
    lateinit var groupChatRecyclerView: RecyclerView
    private lateinit var mRefMessages: DatabaseReference
    private lateinit var mMessagesListener: AppValueEventListener
    private var mListMessages = emptyList<Message>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
        val sendMessageButton = view?.findViewById<ImageButton>(R.id.send)
        val messageForSend = view?.findViewById<EditText>(R.id.message_for_send)
        val helper = SQLiteHelper(context!!)
        val user = helper.getUser()
        sendMessageButton!!.setOnClickListener {
            val message = messageForSend!!.text.toString()
            if (message.isEmpty()){
                makeToast(context!!,"Pysto")
            } else sendGroupMessage(message, context!!){
                  messageForSend.setText("")
            }
        }
    }

    private fun initRecyclerView() {
        groupChatRecyclerView = view?.findViewById(R.id.chatRecyclerView)!!
        groupChatRecyclerView.layoutManager = LinearLayoutManager(context)
        val helper = SQLiteHelper(context!!)
        val user = helper.getUser()
        mAdapter = GroupChatAdapter(helper)
        groupChatRecyclerView.adapter = mAdapter
        mRefMessages = REF_DATABASE_ROOT.child(NODE_GROUP_MESSAGE)
        mMessagesListener = AppValueEventListener { dataSnapshot ->
            mListMessages = dataSnapshot.children.map { it.getValue(Message::class.java)!! }
            mAdapter.setList(mListMessages)
            groupChatRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefMessages.addValueEventListener(mMessagesListener)
        groupChatRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
    }
}