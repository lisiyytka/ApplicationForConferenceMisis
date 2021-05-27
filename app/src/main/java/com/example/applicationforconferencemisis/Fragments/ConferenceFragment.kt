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
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Activities.fragmentName
import com.example.applicationforconferencemisis.Activities.lastDateBtn
import com.example.applicationforconferencemisis.Activities.lastFragment
import com.example.applicationforconferencemisis.Activities.lastFragmentString
import com.example.applicationforconferencemisis.Data.Firebase.*
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.Note
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.makeToast
import com.google.android.material.card.MaterialCardView

class ConferenceFragment(conferenceFromSchedule: Conference) : Fragment() {

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
        initFirebase()
        val nameConference = view!!.findViewById<TextView>(R.id.name)
        val themeConference = view!!.findViewById<TextView>(R.id.text)
        val dateConference = view!!.findViewById<TextView>(R.id.date)
        val nameSpeakerConference = view!!.findViewById<TextView>(R.id.name_speaker)
        val addButton = view!!.findViewById<Button>(R.id.add_btn)
        val editNote = view!!.findViewById<EditText>(R.id.make_note)
        val acceptNote = view!!.findViewById<CardView>(R.id.edit)
        val helper = SQLiteHelper(context!!)
        nameConference.text = conference.name
        themeConference.text = conference.theme
        dateConference.text = conference.date
        nameSpeakerConference.text = conference.speakers
        lastFragmentString = "ScheduleAndMyScheduleFragment"
        REF_DATABASE_ROOT.child(NODE_NOTE).child(helper.getUser().name).child(lastDateBtn)
            .child(fragmentName!!.text.toString())
            .child(conference.conferenceId).addListenerForSingleValueEvent(
                AppValueEventListener {
                    editNote.setText(it.getValue(String::class.java))
                })


        addButton.setOnClickListener {
            helper.insertConferenceToSchedule(conference.conferenceId)
            REF_DATABASE_ROOT.child(NODE_USERS).child(helper.getUser().username).child(
                NODE_PERSONAL_SCHEDULE
            ).child(lastDateBtn).child(fragmentName!!.text.toString()).child(conference.conferenceId).setValue(conference)
        }

        acceptNote.setOnClickListener {
            val note = Note()
            if (editNote.text.isEmpty()) {
                makeToast(context!!, "Fill the field")
            } else {
                note.conference = conference.name
                note.text = editNote.text.toString()
                REF_DATABASE_ROOT.child(NODE_NOTE).child(helper.getUser().name)
                    .child(lastDateBtn)
                    .child(fragmentName!!.text.toString())
                    .child(conference.conferenceId).setValue(note.text)
            }
        }
    }
}