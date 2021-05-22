package com.example.applicationforconferencemisis.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_MESSAGES
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Firebase.sendMessage
import com.example.applicationforconferencemisis.Data.Models.Message
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.Adapters.SingleChatAdapter
import com.example.applicationforconferencemisis.makeToast
import com.google.firebase.database.DatabaseReference

class SingleChatFragment(val userId: String) : Fragment() {

    lateinit var mAdapter: SingleChatAdapter
    lateinit var singleChatRecyclerView: RecyclerView
    private lateinit var mReceivingUser: User
    private lateinit var mRefUser: DatabaseReference
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
        sendMessageButton!!.setOnClickListener {
            val message = messageForSend!!.text.toString()
            if (message.isEmpty()) {
                makeToast(context!!, "Pysto")
            } else sendMessage(message, userId, context!!) {
                messageForSend.setText("")
            }
        }
    }

    private fun initRecyclerView() {
        val helper = SQLiteHelper(context!!)
        singleChatRecyclerView = view?.findViewById(R.id.chatRecyclerView)!!
        singleChatRecyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = SingleChatAdapter(helper)
        singleChatRecyclerView.adapter = mAdapter
        mRefMessages = REF_DATABASE_ROOT
            .child(NODE_MESSAGES)
            .child(helper.getUser().username)
            .child(userId)
        mMessagesListener = AppValueEventListener { dataSnapshot ->
            mListMessages = dataSnapshot.children.map { it.getValue(Message::class.java)!! }
            mAdapter.setList(mListMessages)
            singleChatRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefMessages.addValueEventListener(mMessagesListener)
    }
}
