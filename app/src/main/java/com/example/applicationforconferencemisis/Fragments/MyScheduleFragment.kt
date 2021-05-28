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
import com.example.applicationforconferencemisis.Activities.lastBtnId
import com.example.applicationforconferencemisis.Activities.lastFragment
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_PERSONAL_SCHEDULE
import com.example.applicationforconferencemisis.Data.Firebase.NODE_USERS
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.MainSchedule
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.makeToast
import com.example.applicationforconferencemisis.replaceFragment

class MyScheduleFragment : Fragment() {

    lateinit var adapter: RecyclerView.Adapter<MyViewHolder>
    lateinit var myScheduleRecyclerView: RecyclerView
    var date = "june 3"
    var event = "Workshops"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_schedule, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        val helper = SQLiteHelper(context!!)
        val dateThirdForMySchedule = view!!.findViewById<TextView>(R.id.third_date_for_my_schedule)
        val dateFourthForMySchedule =
            view!!.findViewById<TextView>(R.id.fourth_date_for_my_schedule)
        val workshopsFourthForMySchedule =
            view!!.findViewById<TextView>(R.id.workshops_for_my_schedule)
        val sessionsFourthForMySchedule =
            view!!.findViewById<TextView>(R.id.sessions_for_my_schedule)

        REF_DATABASE_ROOT.child(NODE_USERS)
            .child(helper.getUser().username)
            .child(NODE_PERSONAL_SCHEDULE)
            .child(date)
            .child(event).addListenerForSingleValueEvent(
                AppValueEventListener {
                    var mListMySchedule = emptyList<Conference?>()
                    mListMySchedule = it.children.map { it.getValue(Conference::class.java) }
                    initRecyclerView(mListMySchedule)
                }
            )

        dateThirdForMySchedule.setOnClickListener {
            dateThirdForMySchedule.setTextAppearance(R.style.selected_day)
            dateFourthForMySchedule.setTextAppearance(R.style.unselected_day)
            date = "june 3"
            REF_DATABASE_ROOT.child(NODE_USERS)
                .child(helper.getUser().username)
                .child(NODE_PERSONAL_SCHEDULE)
                .child(date)
                .child(event).addListenerForSingleValueEvent(
                    AppValueEventListener {
                        var mListMySchedule = emptyList<Conference?>()
                        mListMySchedule = it.children.map { it.getValue(Conference::class.java) }
                        initRecyclerView(mListMySchedule)
                    }
                )
        }

        dateFourthForMySchedule.setOnClickListener {
            dateFourthForMySchedule.setTextAppearance(R.style.selected_day)
            dateThirdForMySchedule.setTextAppearance(R.style.unselected_day)
            date = "june 4"
            REF_DATABASE_ROOT.child(NODE_USERS)
                .child(helper.getUser().username)
                .child(NODE_PERSONAL_SCHEDULE)
                .child(date)
                .child(event).addListenerForSingleValueEvent(
                    AppValueEventListener {
                        var mListMySchedule = emptyList<Conference?>()
                        mListMySchedule = it.children.map { it.getValue(Conference::class.java) }
                        initRecyclerView(mListMySchedule)
                    }
                )
        }

        workshopsFourthForMySchedule.setOnClickListener {
            workshopsFourthForMySchedule.setTextAppearance(R.style.selected_day)
            sessionsFourthForMySchedule.setTextAppearance(R.style.unselected_day)
            event = "Workshops"
            REF_DATABASE_ROOT.child(NODE_USERS)
                .child(helper.getUser().username)
                .child(NODE_PERSONAL_SCHEDULE)
                .child(date)
                .child(event).addListenerForSingleValueEvent(
                    AppValueEventListener {
                        var mListMySchedule = emptyList<Conference?>()
                        mListMySchedule = it.children.map { it.getValue(Conference::class.java) }
                        initRecyclerView(mListMySchedule)
                    }
                )
        }

        sessionsFourthForMySchedule.setOnClickListener {
            workshopsFourthForMySchedule.setTextAppearance(R.style.unselected_day)
            sessionsFourthForMySchedule.setTextAppearance(R.style.selected_day)
            event = "Sessions"
            REF_DATABASE_ROOT.child(NODE_USERS)
                .child(helper.getUser().username)
                .child(NODE_PERSONAL_SCHEDULE)
                .child(date)
                .child(event).addListenerForSingleValueEvent(
                    AppValueEventListener {
                        var mListMySchedule = emptyList<Conference?>()
                        mListMySchedule = it.children.map { it.getValue(Conference::class.java) }
                        initRecyclerView(mListMySchedule)
                    }
                )
        }

    }

    private fun initRecyclerView(events: List<Conference?>){
        myScheduleRecyclerView = view!!.findViewById(R.id.scheduleRecyclerView)
        myScheduleRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = object : RecyclerView.Adapter<MyViewHolder>(){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.schedule_component, parent, false)
                return MyViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.eventTime.text = events[position]!!.date
                holder.eventName.text = events[position]!!.name
                holder.eventDescription.text = events[position]!!.theme
                holder.eventSpeaker.text = events[position]!!.speakers
                holder.itemView.setOnClickListener {
                    lastFragment = MyScheduleFragment()
                    lastBtnId = R.id.my_schedule_btn
                    replaceFragment(ConferenceFragment(events[position]!!, date, event))
                }
            }

            override fun getItemCount(): Int = events.size
        }
        myScheduleRecyclerView.adapter = adapter
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventTime: TextView = itemView.findViewById(R.id.eventTime)
        var eventName: TextView = itemView.findViewById(R.id.eventTitle)
        var eventDescription: TextView = itemView.findViewById(R.id.eventDescription)
        var eventSpeaker: TextView = itemView.findViewById(R.id.eventSpeaker)
    }

}