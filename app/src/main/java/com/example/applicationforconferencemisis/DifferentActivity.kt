package com.example.applicationforconferencemisis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Data.Models.Conference

class DifferentActivity : AppCompatActivity() {

    private val scheduleFragment = scheduleAndMyScheduleFragment()
    private val groupChatFragment = groupChatFragment()
    private val messagesFragment = messagesFragment()
    private val membersAndSpeakersFragment = membersAndSpeakersFragment()
    private val accountFragment = accountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        val backBtn = findViewById<ImageView>(R.id.back)
        backBtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        val buttonId = intent.getIntExtra("buttonId", 0)

        val fragmentManager = supportFragmentManager.beginTransaction()
        when (buttonId) {
            R.id.schedule_btn -> fragmentManager.add(R.id.containerForFrag, scheduleFragment)
            R.id.my_schedule_btn -> fragmentManager.add(R.id.containerForFrag, scheduleFragment)
            R.id.group_chat_btn -> fragmentManager.add(R.id.containerForFrag, groupChatFragment)
            R.id.messages_btn -> fragmentManager.add(R.id.containerForFrag, messagesFragment)
            R.id.members_btn -> fragmentManager.add(R.id.containerForFrag, membersAndSpeakersFragment)
            R.id.speakers_btn -> fragmentManager.add(R.id.containerForFrag, membersAndSpeakersFragment)
            R.id.account_btn -> fragmentManager.add(R.id.containerForFrag, accountFragment)
        }
        fragmentManager.commit()
    }
}