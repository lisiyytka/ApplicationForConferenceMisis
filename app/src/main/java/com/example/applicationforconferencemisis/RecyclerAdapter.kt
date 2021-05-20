package com.example.applicationforconferencemisis

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Data.Models.Conference

class RecyclerAdapter(private val events: List<Conference>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var eventTime: TextView? = null
        var eventName: TextView? = null
        var eventDescription: TextView? = null
        var eventSpeaker: TextView? = null

        init {
            eventTime = itemView.findViewById(R.id.eventTime)
            eventName = itemView.findViewById(R.id.eventTitle)
            eventDescription = itemView.findViewById(R.id.eventDescription)
            eventSpeaker = itemView.findViewById(R.id.eventSpeaker)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedule_component, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        for (event in events){
            holder.eventTime?.text = event.date
            holder.eventName?.text = event.name
            holder.eventDescription?.text = event.theme
            holder.eventSpeaker?.text = event.speakers
        }
    }

    override fun getItemCount() = events.size
}