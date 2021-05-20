package com.example.applicationforconferencemisis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Data.Models.Contacts

class MessagesRecyclerAdapter(private val contacts: List<Contacts>) :
    RecyclerView.Adapter<MessagesRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var usersImg: TextView? = null
        var usersName: TextView? = null
        var lastMessage: TextView? = null
        var time: TextView? = null
        var numberOfUnreadMsg: TextView? = null

        init {
//            usersImg = itemView.findViewById(R.id.users_img)
            usersName = itemView.findViewById(R.id.users_name)
            lastMessage = itemView.findViewById(R.id.last_message)
            time = itemView.findViewById(R.id.time)
            numberOfUnreadMsg = itemView.findViewById(R.id.number_of_unread_msg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.messages_rv_component, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.usersImg?.text = contacts[position].usersImg
        holder.usersName?.text = contacts[position].usersName
        holder.lastMessage?.text = contacts[position].lastMessage
        holder.time?.text = contacts[position].time
        holder.numberOfUnreadMsg?.text = contacts[position].numberOfUnreadMsg
        holder.itemView.setOnClickListener {}
    }

    override fun getItemCount() = contacts.size

}