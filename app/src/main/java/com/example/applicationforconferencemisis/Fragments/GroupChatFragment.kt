package com.example.applicationforconferencemisis.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Data.Firebase.sendMessage
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.makeToast

class groupChatFragment(val userId: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onResume() {
        super.onResume()
        val sendMessageButton = view?.findViewById<ImageButton>(R.id.send)
        val messageForSend = view?.findViewById<EditText>(R.id.message_for_send)
        sendMessageButton!!.setOnClickListener {
            val message = messageForSend!!.text.toString()
            if (message.isEmpty()){
                makeToast(context!!,"Pysto")
            } else sendMessage(message, userId,context!!){
                  messageForSend.setText("")
            }
        }
    }


}