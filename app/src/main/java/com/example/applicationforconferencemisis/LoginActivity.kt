package com.example.applicationforconferencemisis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val buttonForLogin = findViewById<ImageButton>(R.id.buttonForLogin)
        buttonForLogin.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}