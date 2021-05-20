package com.example.applicationforconferencemisis

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.applicationforconferencemisis.Data.Firebase.addNewUser
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null
    private var mMessageListener: ChildEventListener? = null
    val messageList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val user = User("debil", "df", "fd", "fd")
//        addNewUser(user)

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
//        coroutineGetUser(this)
        val helper = SQLiteHelper(this)
        val user = helper.getUser()
        makeToast(this,user.username+" "+user.password)
    }

//    private fun coroutineGetUser(context: Context) = runBlocking {
//        getUser(context,"debil")
//        val user = async {getUserFromLocalDatabase(context)}
//        makeToast(context, user.await().username)
//    }
//
//    suspend fun getUserFromLocalDatabase(context: Context): User{
//        delay(2000)
//        val localDatabaseHelper = SQLiteHelper(context)
//        val user = localDatabaseHelper.getUser()
//        return user
//    }

    fun startDifActivity (id: Int)
    {
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

        mMessageReference!!.addChildEventListener(childEventListener)
        mMessageListener = childEventListener
    }

}