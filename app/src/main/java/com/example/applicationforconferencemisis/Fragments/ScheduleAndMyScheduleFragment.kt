package com.example.applicationforconferencemisis.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Activities.lastBtnId
import com.example.applicationforconferencemisis.Activities.lastFragment
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.replaceFragment

class scheduleAndMyScheduleFragment: Fragment() {

    lateinit var adapter: RecyclerView.Adapter<MyViewHolder>
    lateinit var scheduleRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onStart(){
        super.onStart()

        val helper = SQLiteHelper(context!!)

        initRecyclerView(helper.getAllConferences())
    }


    private fun initRecyclerView(events: List<Conference>){
        scheduleRecyclerView = view?.findViewById(R.id.scheduleRecyclerView)!!
        scheduleRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = object : RecyclerView.Adapter<MyViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.schedule_component, parent, false)
                return MyViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.eventTime.text = events[position].date
                holder.eventName.text = events[position].name
                holder.eventDescription.text = events[position].theme
                holder.eventSpeaker.text = events[position].speakers
                holder.itemView.setOnClickListener {
                    lastFragment = scheduleAndMyScheduleFragment()
                    lastBtnId = R.id.schedule_btn
                    replaceFragment(conferenceFragment())
                }
            }

            override fun getItemCount() = events.size
        }
        scheduleRecyclerView.adapter = adapter
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventTime: TextView = itemView.findViewById(R.id.eventTime)
        var eventName: TextView = itemView.findViewById(R.id.eventTitle)
        var eventDescription: TextView = itemView.findViewById(R.id.eventDescription)
        var eventSpeaker: TextView = itemView.findViewById(R.id.eventSpeaker)
    }
}