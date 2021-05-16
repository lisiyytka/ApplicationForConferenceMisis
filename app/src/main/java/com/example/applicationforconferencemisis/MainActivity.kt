package com.example.applicationforconferencemisis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scheduleButton = findViewById<Button>(R.id.schedule_btn)
        scheduleButton.setOnClickListener {
            startActivity(Intent(this, DifferentActivity::class.java))
        }
    }
}