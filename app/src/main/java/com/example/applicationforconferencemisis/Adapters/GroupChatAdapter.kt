package com.example.applicationforconferencemisis.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Data.Models.Message
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.asTime

class GroupChatAdapter (private val helper: SQLiteHelper):
    RecyclerView.Adapter<GroupChatAdapter.GroupChatHolder>(){

    private var mListMessagesCache = emptyList<Message>()

    class GroupChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        val blocUserMessage: ConstraintLayout = view.findViewById(R.id.bloc_user_message)
        val myMsg: TextView = view.findViewById(R.id.my_message)
        val myMsgTime: TextView = view.findViewById(R.id.my_time)

        val blocReceivedMessage: ConstraintLayout = view.findViewById(R.id.bloc_received_message)
        val msg: TextView = view.findViewById(R.id.message)
        val msgTime: TextView = view.findViewById(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg, parent, false)
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
        }
    }

    override fun getItemCount(): Int = mListMessagesCache.size

    fun setList(list: List<Message>) {
        mListMessagesCache = list
        notifyDataSetChanged()
    }
}