package com.example.applicationforconferencemisis.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Activities.lastFragment
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.R

class ConferenceFragment(conferenceFromSchedule: Conference): Fragment() {

    var conference = conferenceFromSchedule
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onResume() {
        super.onResume()
        val nameConference = view!!.findViewById<TextView>(R.id.name)
        val themeConference = view!!.findViewById<TextView>(R.id.text)
        val dateConference = view!!.findViewById<TextView>(R.id.date)
        val nameSpeakerConference = view!!.findViewById<TextView>(R.id.name_speaker)
        val addButton = view!!.findViewById<Button>(R.id.add_btn)
        val editNote = view!!.findViewById<EditText>(R.id.make_note)
        nameConference.text = conference.name
        themeConference.text = conference.theme
        dateConference.text = conference.date
        nameSpeakerConference.text = conference.speakers
//        lastFragment = ScheduleAndMyScheduleFragment().
    }
}