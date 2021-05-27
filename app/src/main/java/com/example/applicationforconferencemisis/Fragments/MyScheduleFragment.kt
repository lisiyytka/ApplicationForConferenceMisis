package com.example.applicationforconferencemisis.Fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.R

class MyScheduleFragment : Fragment() {
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
        val dateThirdForMySchedule = view!!.findViewById<TextView>(R.id.third_date_for_my_schedule)
        val dateFourthForMySchedule =
            view!!.findViewById<TextView>(R.id.fourth_date_for_my_schedule)
        val workshopsFourthForMySchedule =
            view!!.findViewById<TextView>(R.id.workshops_for_my_schedule)
        val sessionsFourthForMySchedule =
            view!!.findViewById<TextView>(R.id.sessions_for_my_schedule)

        var is03 = true
        var isWorkshops = true

        dateThirdForMySchedule.setOnClickListener {
            dateThirdForMySchedule.setTextAppearance(R.style.selected_day)
            dateFourthForMySchedule.setTextAppearance(R.style.unselected_day)
            is03 = true
            if(isWorkshops){}
        }

        dateFourthForMySchedule.setOnClickListener {
            dateFourthForMySchedule.setTextAppearance(R.style.selected_day)
            dateThirdForMySchedule.setTextAppearance(R.style.unselected_day)
            is03 = false
        }

        workshopsFourthForMySchedule.setOnClickListener {
            workshopsFourthForMySchedule.setTextAppearance(R.style.selected_day)
            sessionsFourthForMySchedule.setTextAppearance(R.style.unselected_day)
            isWorkshops = true
        }

        sessionsFourthForMySchedule.setOnClickListener {
            workshopsFourthForMySchedule.setTextAppearance(R.style.unselected_day)
            sessionsFourthForMySchedule.setTextAppearance(R.style.selected_day)
            isWorkshops = false
        }

    }
}