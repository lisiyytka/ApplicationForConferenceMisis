package com.example.applicationforconferencemisis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Data.Models.Contacts
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper

class messagesFragment: Fragment() {
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

        val messagesRecyclerView = view?.findViewById<RecyclerView>(R.id.messagesRecyclerView)
        messagesRecyclerView!!.layoutManager = LinearLayoutManager(context)
        messagesRecyclerView.adapter = MessagesRecyclerAdapter(contacts())

    }

    private fun contacts(): List<Contacts> {
        val c = mutableListOf<Contacts>()
        val a = Contacts("Arseniy Nikorkin", "If you have any question", "10:10", "2")
        val b = Contacts("Roman Gritchenko", "Hi! I have question about", "20:15", "1")
        c.add(a)
        c.add(b)
        return c
    }
}