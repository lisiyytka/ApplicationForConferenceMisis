package com.example.applicationforconferencemisis.Activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.applicationforconferencemisis.*
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_CONFERENCES
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Firebase.getUserContactsFromFirebase
import com.example.applicationforconferencemisis.Data.Models.MainSchedule
import com.example.applicationforconferencemisis.Data.Models.Message
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null
    private var mMessageListener: ChildEventListener? = null
    val messageList = ArrayList<User>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        val helper = SQLiteHelper(this)
        val user = helper.getUser()
        if (user.name == "")
            startActivity(Intent(this, RegisterActivity::class.java))
        val instant: Instant = Instant.now()
        val zoneId: ZoneId = ZoneId.of("Europe/Moscow")
        val zdt: ZonedDateTime = ZonedDateTime.ofInstant(instant, zoneId)

        val formmaterForSdf: DateTimeFormatter = DateTimeFormatter.ofPattern("dd")
        val currentDate = zdt.format(formmaterForSdf)
        ////
        val qwe = DateTimeFormatter.ofPattern("HH")
        val currentHours = zdt.format(qwe)
        /////
        val asd = DateTimeFormatter.ofPattern("mm")
        val currentMinutes = zdt.format(asd)
        /////
        val mainName = findViewById<TextView>(R.id.name_main)
        val mainDate = findViewById<TextView>(R.id.date_main)
        if (currentDate.toString() != "03" && currentDate.toString() != "04" && currentDate.toString() != "05") {
            REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 3")
                .child("Schedule")
                .child("0").addListenerForSingleValueEvent(
                    AppValueEventListener {
                        val result = it.getValue(MainSchedule::class.java)
                        if (result!!.name.length > 71){
                            val a = result.name.split(":")
                            mainName.text = a[0]
                        }else {
                            mainName.text = result.name
                        }
                        mainDate.text = result.date
                    }
                )
        } else {
            when (currentDate) {
                "03" -> {
                    REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 3").child("Schedule")
                        .addListenerForSingleValueEvent(
                            AppValueEventListener {
                                val juneThirdSchedule =
                                    it.children.map { it.getValue(MainSchedule::class.java) }
                                for (i in juneThirdSchedule) {
                                    val a = i!!.date.split("-")[0].split(":")
                                    if (currentHours.toInt() <= a[0].toInt()) {
                                        if (currentHours.toInt() == a[0].toInt()) {
                                            if (currentMinutes.toInt() <= a[1].toInt()) {
                                                if (i.name.length > 71){
                                                    val a = i.name.split(":")
                                                    mainName.text = a[0]
                                                }else {
                                                    mainName.text = i.name
                                                }
                                                mainDate.text = i.date
                                                break
                                            }
                                        } else {
                                            mainName.text = i.name
                                            mainDate.text = i.date
                                            break
                                        }
                                    }

                                }

                            }
                        )
                }
                "04" -> {
                    REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 4").child("Schedule")
                        .addListenerForSingleValueEvent(
                            AppValueEventListener {
                                val juneFourthSchedule =
                                    it.children.map { it.getValue(MainSchedule::class.java) }
                                for (i in juneFourthSchedule) {
                                    val a = i!!.date.split("-")[0].split(":")
                                    if (currentHours.toInt() <= a[0].toInt()) {
                                        if (currentHours.toInt() == a[0].toInt()) {
                                            if (currentMinutes.toInt() <= a[1].toInt()) {
                                                if (i.name.length > 71){
                                                    val a = i.name.split(":")
                                                    mainName.text = a[0]
                                                }else {
                                                    mainName.text = i.name
                                                }
                                                mainDate.text = i.date
                                                break
                                            }
                                        } else {
                                            mainName.text = i.name
                                            mainDate.text = i.date
                                            break
                                        }
                                    }

                                }
                            }
                        )
                }
                "05" -> {
                    REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 5").child("Schedule")
                        .addListenerForSingleValueEvent(
                            AppValueEventListener {
                                val juneFifthSchedule =
                                    it.children.map { it.getValue(MainSchedule::class.java) }
                                for (i in juneFifthSchedule) {
                                    val a = i!!.date.split("-")[0].split(":")
                                    if (currentHours.toInt() <= a[0].toInt()) {
                                        if (currentHours.toInt() == a[0].toInt()) {
                                            if (currentMinutes.toInt() <= a[1].toInt()) {
                                                if (i.name.length > 71){
                                                    val a = i.name.split(":")
                                                    mainName.text = a[0]
                                                }else {
                                                    mainName.text = i.name
                                                }
                                                mainDate.text = i.date

                                                break
                                            }
                                        } else {
                                            mainName.text = i.name
                                            mainDate.text = i.date
                                            break
                                        }
                                    }

                                }
                            }
                        )
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR

        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("Users")
        firebaseListenerInit(this)
        val helper = SQLiteHelper(this)
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
            helper.deleteGroupContacts()
            getUserContactsFromFirebase(this, helper.getUser().username)
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

        addUsers()
        setMainSchedule()
        setConferencesWorkshop()
        setConferencesSessions()
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