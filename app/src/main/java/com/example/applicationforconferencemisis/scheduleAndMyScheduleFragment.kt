package com.example.applicationforconferencemisis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper

class scheduleAndMyScheduleFragment: Fragment() {
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

        val scheduleRecyclerView = view?.findViewById<RecyclerView>(R.id.scheduleRecyclerView)
        scheduleRecyclerView!!.layoutManager = LinearLayoutManager(context)
        scheduleRecyclerView.adapter = ScheduleRecyclerAdapter(helper.getAllConferences())
    }
}