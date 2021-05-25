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
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_CONFERENCES
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.MainSchedule
import com.example.applicationforconferencemisis.Data.Models.Message
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.replaceFragment

class MainScheduleFragment: Fragment() {

    lateinit var adapter: RecyclerView.Adapter<MyViewHolder>
    lateinit var mainScheduleRecyclerView: RecyclerView
    lateinit var mListMainSchedule: List<MainSchedule>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_schedule, container, false)
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        mainScheduleRecyclerView = view?.findViewById(R.id.mainScheduleRecyclerView)!!
        mainScheduleRecyclerView.layoutManager = LinearLayoutManager(context)
        AppValueEventListener { dataSnapshot ->
            mListMainSchedule = dataSnapshot.children.map { it.getValue(MainSchedule::class.java)!! }
//            adapter.set(mListMainSchedule)
        }
        adapter = object : RecyclerView.Adapter<MyViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.schedule_component, parent, false)
                return MyViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

                REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 3").child("Schedule").child(position.toString()).addListenerForSingleValueEvent(
                    AppValueEventListener {
                        val mainSchedule = it.getValue(MainSchedule::class.java)
                        holder.time.text = mainSchedule!!.date
                        holder.event.text = mainSchedule!!.name
                        if (mainSchedule.name == "Workshops (see detailed schedule)" || mainSchedule.name == "Concurrent Sessions (see detailed schedule)") {
                            holder.event.setOnClickListener {
                                lastFragment = MainScheduleFragment()
                                lastBtnId = R.id.schedule_btn
                                replaceFragment(ScheduleAndMyScheduleFragment())
                            }
                        }
                    }
                )
            }

            override fun getItemCount(): Int {
                TODO("Not yet implemented")
            }

        }
        mainScheduleRecyclerView.adapter = adapter
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var time: TextView = itemView.findViewById(R.id.time)
        var event: TextView = itemView.findViewById(R.id.event)
    }
}