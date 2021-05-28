package com.example.applicationforconferencemisis.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_CONFERENCES
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Models.MainSchedule
import com.example.applicationforconferencemisis.Data.Models.Message
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.addUsers
import com.example.applicationforconferencemisis.asDate
import com.example.applicationforconferencemisis.makeToast
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null
    private var mMessageListener: ChildEventListener? = null
    val messageList = ArrayList<User>()

    override fun onResume() {
        super.onResume()
        val helper = SQLiteHelper(this)
        val user = helper.getUser()
        if (user.name == "")
            startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("Users")
        firebaseListenerInit(this)

        val scheduleButton = findViewById<Button>(R.id.schedule_btn)
        scheduleButton.setOnClickListener {
            startDifActivity(scheduleButton.id)
        }

        val myScheduleButton = findViewById<Button>(R.id.my_schedule_btn)
        myScheduleButton.setOnClickListener {
            startDifActivity(myScheduleButton.id)
        }

        val groupChatButton = findViewById<Button>(R.id.group_chat_btn)
        groupChatButton.setOnClickListener {
            startDifActivity(groupChatButton.id)
        }

        val messagesButton = findViewById<Button>(R.id.messages_btn)
        messagesButton.setOnClickListener {
            startDifActivity(messagesButton.id)
        }

        val membersButton = findViewById<Button>(R.id.members_btn)
        membersButton.setOnClickListener {
            startDifActivity(membersButton.id)
        }

        val speakersButton = findViewById<Button>(R.id.speakers_btn)
        speakersButton.setOnClickListener {
            startDifActivity(speakersButton.id)
        }

        val accountButton = findViewById<Button>(R.id.account_btn)
        accountButton.setOnClickListener {
            startDifActivity(accountButton.id)
        }

        val upcomingConferenceButton = findViewById<LinearLayout>(R.id.upcoming_conference)
        upcomingConferenceButton.setOnClickListener {
            startDifActivity(upcomingConferenceButton.id)
        }
        addUsers()
        val sdf = SimpleDateFormat("dd", Locale.getDefault())
        val currentDate = sdf.format(Date())
        val qwe = SimpleDateFormat("hh", Locale.getDefault())
        val currentHours = qwe.format(Date())
        val asd = SimpleDateFormat("mm", Locale.getDefault())
        val currentMinutes = qwe.format(Date())


        if (currentDate == "03") {
            REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 3").child("Schedule")
                .addListenerForSingleValueEvent(
                    AppValueEventListener {
                        val juneThirdSchedule =
                            it.children.map { it.getValue(MainSchedule::class.java) }
                        for (i in juneThirdSchedule) {
                            val a = i!!.date.split("-")
                            val b = a[0].split(":")
                            if (currentHours.toInt() > b[0].toInt()){

                            }
                        }
                    }
                )
        } else if (currentDate == "04") {
            REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 4").child("Schedule")
                .addListenerForSingleValueEvent(
                    AppValueEventListener {
                        val juneFourthSchedule =
                            it.children.map { it.getValue(MainSchedule::class.java) }
                    }
                )
        } else if (currentDate == "05") {
            REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 5").child("Schedule")
                .addListenerForSingleValueEvent(
                    AppValueEventListener {
                        val juneFifthSchedule =
                            it.children.map { it.getValue(MainSchedule::class.java) }
                    }
                )
        }
    }


    fun startDifActivity(id: Int) {
        startActivity(Intent(this, DifferentActivity::class.java).putExtra("buttonId", id))
    }

    private fun firebaseListenerInit(context: Context) {

        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                // A new message has been added
                // onChildAdded() will be called for each node at the first time
                val message = snapshot.getValue(User::class.java)
                messageList.add(message!!)

                Log.e("TAG", "onChildAdded:" + message.username)

                val latest = messageList[messageList.size - 1]
//                makeToast(context, latest.username)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

    }


}