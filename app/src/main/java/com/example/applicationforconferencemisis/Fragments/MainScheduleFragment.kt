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
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.replaceFragment

class MainScheduleFragment: Fragment() {

    lateinit var adapter: RecyclerView.Adapter<MyViewHolder>
    lateinit var mainScheduleRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    private fun initRecyclerView(events: List<Conference>){
        mainScheduleRecyclerView = view?.findViewById(R.id.mainScheduleRecyclerView)!!
        mainScheduleRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = object : RecyclerView.Adapter<MyViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.schedule_component, parent, false)
                return MyViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.time.text = events[position].date
                holder.event.text = events[position].name
                if (events[position].name == "Workshops (see detailed schedule)" || events[position].name == "Concurrent Sessions (see detailed schedule)"){
                    holder.event.setOnClickListener {
                        lastFragment = MainScheduleFragment()
                        lastBtnId = R.id.schedule_btn
                        replaceFragment(ScheduleAndMyScheduleFragment())
                    }
                }

            }

            override fun getItemCount() = events.size
        }
        mainScheduleRecyclerView.adapter = adapter
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var time: TextView = itemView.findViewById(R.id.time)
        var event: TextView = itemView.findViewById(R.id.event)
    }
}