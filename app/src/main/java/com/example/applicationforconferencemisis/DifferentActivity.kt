package com.example.applicationforconferencemisis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DifferentActivity : AppCompatActivity() {

    private val scheduleFragment = scheduleAndMyScheduleFragment()
    private val groupChatFragment = groupChatFragment()
    private val messagesFragment = messagesFragment()
    private val membersAndSpeakersFragment = membersAndSpeakersFragment()
    private val accountFragment = accountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)
    }

    fun onClick(view: View) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        when (view.id) {
            R.id.schedule_btn -> fragmentManager.add(R.id.containerForFrag, scheduleFragment)
            R.id.my_schedule_btn -> fragmentManager.replace(R.id.containerForFrag, scheduleFragment)
            R.id.group_chat_btn -> fragmentManager.replace(R.id.containerForFrag, groupChatFragment)
            R.id.messages_btn -> fragmentManager.replace(R.id.containerForFrag, messagesFragment)
            R.id.members_btn -> fragmentManager.replace(R.id.containerForFrag, membersAndSpeakersFragment)
            R.id.speakers_btn -> fragmentManager.replace(R.id.containerForFrag, membersAndSpeakersFragment)
            R.id.account_btn -> fragmentManager.replace(R.id.containerForFrag, accountFragment)
        }
        fragmentManager.commit()
    }
}