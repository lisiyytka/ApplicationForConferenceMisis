package com.example.applicationforconferencemisis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.applicationforconferencemisis.Data.Firebase.addNewUser
import com.example.applicationforconferencemisis.Data.Firebase.initFirebase
import com.example.applicationforconferencemisis.Data.Models.User

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val buttonForLogin = findViewById<ImageButton>(R.id.buttonForLogin)
        val editTextEmail = findViewById<EditText>(R.id.edit_text_email)
        val editTextPassword = findViewById<EditText>(R.id.edit_text_password)
        initFirebase()
        buttonForLogin.setOnClickListener{
            if (editTextEmail.text.isNotEmpty()&&editTextPassword.text.isNotEmpty()){
                val user = User(editTextEmail.text.toString(),
                    editTextPassword.text.toString(),"asd","123123")
                addNewUser(user)
            }else{
                makeToast(this,"Fill in the fields!")
            }
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


}