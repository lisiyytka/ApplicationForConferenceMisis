package com.example.applicationforconferencemisis.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.Fragments.scheduleAndMyScheduleFragment
import com.example.applicationforconferencemisis.Fragments.groupChatFragment
import com.example.applicationforconferencemisis.Fragments.singleChatFragment
import com.example.applicationforconferencemisis.Fragments.messagesFragment
import com.example.applicationforconferencemisis.Fragments.membersAndSpeakersFragment
import com.example.applicationforconferencemisis.Fragments.accountFragment
import com.example.applicationforconferencemisis.Fragments.conferenceFragment


var lastFragment: Fragment? = null
var lastBtnId: Int = 0
var fragmentName: TextView? = null

class DifferentActivity : AppCompatActivity() {

    private val scheduleFragment = scheduleAndMyScheduleFragment()
    private val groupChatFragment = groupChatFragment("123")
    private val singleChatFragment = singleChatFragment("123")
    private val messagesFragment = messagesFragment()
    private val membersAndSpeakersFragment = membersAndSpeakersFragment()
    private val accountFragment = accountFragment()
    private val conferenceFragment = conferenceFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        val backBtn = findViewById<ImageView>(R.id.back)
        backBtn.setOnClickListener{
            if (lastFragment == null){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this,DifferentActivity::class.java).putExtra("buttonId", lastBtnId))
            }
        }

        fragmentName = findViewById(R.id.fragment_name)

        val buttonId = intent.getIntExtra("buttonId", 0)
        val fragmentManager = supportFragmentManager.beginTransaction()
        when (buttonId) {
            R.id.schedule_btn -> {
                lastFragment = null
                fragmentName!!.text = "Schedule"
                fragmentManager.add(R.id.containerForFrag, scheduleFragment)
            }
            R.id.my_schedule_btn -> {
                lastFragment = null
                fragmentName!!.text = "My schedule"
                fragmentManager.add(R.id.containerForFrag, scheduleFragment)
            }
            R.id.group_chat_btn -> {
                lastFragment = null
                fragmentName!!.text = "Group chat"
                fragmentManager.add(R.id.containerForFrag, groupChatFragment)
            }
            R.id.messages_btn -> {
                lastFragment = null
                fragmentName!!.text = "Contacts"
                fragmentManager.add(R.id.containerForFrag, messagesFragment)
            }
            R.id.members_btn -> {
                lastFragment = null
                fragmentName!!.text = "Members"
                fragmentManager.add(R.id.containerForFrag, membersAndSpeakersFragment)
            }
            R.id.speakers_btn -> {
                lastFragment = null
                fragmentName!!.text = "Speakers"
                fragmentManager.add(R.id.containerForFrag, membersAndSpeakersFragment)
            }
            R.id.account_btn -> {
                lastFragment = null
                fragmentName!!.text = "Account"
                fragmentManager.add(R.id.containerForFrag, accountFragment)
            }
            R.id.upcoming_conference -> {
                lastFragment = null
                fragmentName!!.text = ""
                fragmentManager.add(R.id.containerForFrag, conferenceFragment)
            }
        }
        fragmentManager.commit()
    }
}