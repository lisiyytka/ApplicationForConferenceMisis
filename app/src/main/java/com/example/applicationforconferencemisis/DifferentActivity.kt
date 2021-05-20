package com.example.applicationforconferencemisis

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Data.Models.Conference

var lastFragment: Fragment? = null
var lastBtnId: Int = 0

class DifferentActivity : AppCompatActivity() {

    private val scheduleFragment = scheduleAndMyScheduleFragment()
    private val groupChatFragment = groupChatFragment("123")
    private val messagesFragment = messagesFragment()
    private val membersAndSpeakersFragment = membersAndSpeakersFragment()
    private val accountFragment = accountFragment()
    private val conferenceFragment = conferenceFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        val fragmentManager = supportFragmentManager.beginTransaction()

        val backBtn = findViewById<ImageView>(R.id.back)
        backBtn.setOnClickListener{
            if (lastFragment == null){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this,DifferentActivity::class.java).putExtra("buttonId", lastBtnId))
            }
        }

        val buttonId = intent.getIntExtra("buttonId", 0)

        when (buttonId) {
            R.id.schedule_btn -> {
                lastFragment = null
                fragmentManager.add(R.id.containerForFrag, scheduleFragment)
            }
            R.id.my_schedule_btn -> {
                lastFragment = null
                fragmentManager.add(R.id.containerForFrag, scheduleFragment)
            }
            R.id.group_chat_btn -> {
                lastFragment = null
                fragmentManager.add(R.id.containerForFrag, groupChatFragment)
            }
            R.id.messages_btn -> {
                lastFragment = null
                fragmentManager.add(R.id.containerForFrag, messagesFragment)
            }
            R.id.members_btn -> {
                lastFragment = null
                fragmentManager.add(R.id.containerForFrag, membersAndSpeakersFragment)
            }
            R.id.speakers_btn -> {
                lastFragment = null
                fragmentManager.add(R.id.containerForFrag, membersAndSpeakersFragment)
            }
            R.id.account_btn -> {
                lastFragment = null
                fragmentManager.add(R.id.containerForFrag, accountFragment)
            }
            R.id.upcoming_conference -> {
                lastFragment = null
                fragmentManager.add(R.id.containerForFrag, conferenceFragment)
            }
        }
        fragmentManager.commit()
    }
}