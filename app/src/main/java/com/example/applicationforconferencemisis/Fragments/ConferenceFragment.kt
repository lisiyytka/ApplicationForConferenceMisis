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
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_NOTE
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Firebase.initFirebase
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
//        if (fragmentName!!.text == "Workshops"){
//            REF_DATABASE_ROOT.child(NODE_NOTE).child(helper.getUser().name).child("Workshops").child(conference.conferenceId).addListenerForSingleValueEvent(
//                AppValueEventListener{
//                    editNote.setText(it.getValue(String::class.java))
//                }
//            )
//        }else if (fragmentName!!.text == "Sessions"){
//            REF_DATABASE_ROOT.child(NODE_NOTE).child(helper.getUser().name).child("Sessions").child(conference.conferenceId).addListenerForSingleValueEvent(
//                AppValueEventListener{
//                    editNote.setText(it.getValue(String::class.java))
//                }
//            )
//        }

        addButton.setOnClickListener {
            helper.insertConferenceToSchedule(conference.conferenceId)
        }

        acceptNote.setOnClickListener {
            val note = Note()
//            if (editNote.text.isEmpty()) {
//                makeToast(context!!, "Fill the field")
//            } else if (fragmentName!!.text == "Workshops") {
//                note.conference = conference.name
//                note.text = editNote.text.toString()
//                REF_DATABASE_ROOT.child(NODE_NOTE).child(helper.getUser().name).child("Workshops")
//                    .child(conference.conferenceId).setValue(note.text)
//            } else if (fragmentName!!.text == "Sessions") {
//                note.conference = conference.name
//                note.text = editNote.text.toString()
//                REF_DATABASE_ROOT.child(NODE_NOTE).child(helper.getUser().name).child("Sessions")
//                    .child(conference.conferenceId).setValue(note.text)
//            }

            if (editNote.text.isEmpty()) {
                makeToast(context!!, "Fill the field")
            } else if (lastDateBtn == "3") {
                if (fragmentName!!.text == "Workshops") {
                    note.conference = conference.name
                    note.text = editNote.text.toString()
                    REF_DATABASE_ROOT.child(NODE_NOTE).child(helper.getUser().name)
                        .child("june 3")
                        .child("Workshops")
                        .child(conference.conferenceId).setValue(note.text)
                } else if (fragmentName!!.text == "Sessions") {
                    note.conference = conference.name
                    note.text = editNote.text.toString()
                    REF_DATABASE_ROOT.child(NODE_NOTE).child(helper.getUser().name)
                        .child("june 3")
                        .child("Sessions")
                        .child(conference.conferenceId).setValue(note.text)
                }
            } else if (lastDateBtn == "4") {
                if (fragmentName!!.text == "Workshops") {
                    note.conference = conference.name
                    note.text = editNote.text.toString()
                    REF_DATABASE_ROOT.child(NODE_NOTE).child(helper.getUser().name)
                        .child("june 4")
                        .child("Workshops")
                        .child(conference.conferenceId).setValue(note.text)
                } else if (fragmentName!!.text == "Sessions") {
                    note.conference = conference.name
                    note.text = editNote.text.toString()
                    REF_DATABASE_ROOT.child(NODE_NOTE).child(helper.getUser().name)
                        .child("june 4")
                        .child("Sessions")
                        .child(conference.conferenceId).setValue(note.text)
                }
            }
        }
    }
}