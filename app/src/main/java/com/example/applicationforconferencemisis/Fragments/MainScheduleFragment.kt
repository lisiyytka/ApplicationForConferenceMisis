package com.example.applicationforconferencemisis.Fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Activities.*
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_CONFERENCES
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.MainSchedule
import com.example.applicationforconferencemisis.Data.Models.Message
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.replaceFragment

class MainScheduleFragment : Fragment() {

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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        super.onStart()
        val third = view!!.findViewById<TextView>(R.id.third)
        val fourth = view!!.findViewById<TextView>(R.id.fourth)
        val fifth = view!!.findViewById<TextView>(R.id.fifth)
        lastDateBtn = "june 3"
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child(lastDateBtn).child("Schedule")
            .addListenerForSingleValueEvent(
                AppValueEventListener {
                    var mListMainSchedule = emptyList<MainSchedule?>()
                    mListMainSchedule = it.children.map { it.getValue(MainSchedule::class.java) }
                    initRecyclerView(mListMainSchedule)
                })
        third.setOnClickListener {
            third.setTextAppearance(R.style.selected_day)
            fourth.setTextAppearance(R.style.unselected_day)
            fifth.setTextAppearance(R.style.unselected_day)
            lastDateBtn = "june 3"
            REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 3").child("Schedule")
                .addListenerForSingleValueEvent(
                    AppValueEventListener {
                        var mListMainSchedule = emptyList<MainSchedule?>()
                        mListMainSchedule =
                            it.children.map { it.getValue(MainSchedule::class.java) }
                        initRecyclerView(mListMainSchedule)
                    })
        }
        fourth.setOnClickListener {
            third.setTextAppearance(R.style.unselected_day)
            fourth.setTextAppearance(R.style.selected_day)
            fifth.setTextAppearance(R.style.unselected_day)
            lastDateBtn = "june 4"
            REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 4").child("Schedule")
                .addListenerForSingleValueEvent(
                    AppValueEventListener {
                        var mListMainSchedule = emptyList<MainSchedule?>()
                        mListMainSchedule =
                            it.children.map { it.getValue(MainSchedule::class.java) }
                        initRecyclerView(mListMainSchedule)
                    })
        }
        fifth.setOnClickListener {
            third.setTextAppearance(R.style.unselected_day)
            fourth.setTextAppearance(R.style.unselected_day)
            fifth.setTextAppearance(R.style.selected_day)
            lastDateBtn = "june 5"
            REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 5").child("Schedule")
                .addListenerForSingleValueEvent(
                    AppValueEventListener {
                        var mListMainSchedule = emptyList<MainSchedule?>()
                        mListMainSchedule =
                            it.children.map { it.getValue(MainSchedule::class.java) }
                        initRecyclerView(mListMainSchedule)
                    })
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initRecyclerView(list: List<MainSchedule?>) {
        mainScheduleRecyclerView = view?.findViewById(R.id.mainScheduleRecyclerView)!!
        mainScheduleRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = object : RecyclerView.Adapter<MyViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_schedule_component, parent, false)
                return MyViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.time.text = list[position]!!.date
                holder.event.text = list[position]!!.name
                if (list[position]!!.name == "Workshops (see detailed schedule)" || list[position]!!.name == "Concurrent Sessions (see detailed schedule)") {
                    holder.event.setTextAppearance(R.style.font_roboto_medium_purple)
                    holder.event.setOnClickListener {
                        lastFragment = MainScheduleFragment()
                        lastBtnId = R.id.schedule_btn
                        if (list[position]!!.name == "Workshops (see detailed schedule)") {
                            REF_DATABASE_ROOT.child(NODE_CONFERENCES).child(lastDateBtn)
                                .child("Workshop")
                                .addListenerForSingleValueEvent(
                                    AppValueEventListener {
                                        var mListWorkshop = emptyList<Conference?>()
                                        mListWorkshop =
                                            it.children.map { it.getValue(Conference::class.java) }
                                        fragmentName!!.text = "Workshops"
                                        replaceFragment(
                                            ScheduleAndMyScheduleFragment(
                                                mListWorkshop,
                                                lastDateBtn,
                                                "Sessions"
                                            )
                                        )
                                    })
                        } else if (list[position]!!.name == "Concurrent Sessions (see detailed schedule)") {
                            REF_DATABASE_ROOT.child(NODE_CONFERENCES).child(lastDateBtn)
                                .child("Session")
                                .addListenerForSingleValueEvent(
                                    AppValueEventListener {
                                        var mListSession = emptyList<Conference?>()
                                        mListSession =
                                            it.children.map { it.getValue(Conference::class.java) }
                                        fragmentName!!.text = "Sessions"
                                        replaceFragment(
                                            ScheduleAndMyScheduleFragment(
                                                mListSession,
                                                lastDateBtn,
                                                "Sessions"
                                            ),
                                        )
                                    })
                        }
                    }
                }
            }

            override fun getItemCount(): Int = list.size
        }
        mainScheduleRecyclerView.adapter = adapter
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var time: TextView = itemView.findViewById(R.id.time)
        var event: TextView = itemView.findViewById(R.id.event)
    }
}