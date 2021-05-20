package com.example.applicationforconferencemisis

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.applicationforconferencemisis.Data.Firebase.*
import com.example.applicationforconferencemisis.Data.Models.StatusLogin
import com.example.applicationforconferencemisis.Data.Models.StatusLogin.*
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initFirebase()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val helper = SQLiteHelper(this)
//        val user = helper.getUser()
//        if (user.username!= ""){
//            startActivity(Intent(this, MainActivity::class.java))
//        }
        val buttonForLogin = findViewById<ImageButton>(R.id.buttonForLogin)
        val editTextEmail = findViewById<EditText>(R.id.edit_text_email)
        val editTextPassword = findViewById<EditText>(R.id.edit_text_password)
        buttonForLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val pswrd = editTextPassword.text.toString()
            if (email.isEmpty() || pswrd.isEmpty())
                makeToast(this, "Fill all the fields")
            else {
                val ref = REF_DATABASE_ROOT.child(NODE_USERS)
                ref.addListenerForSingleValueEvent(
                    AppValueEventListener {
                        if (it.hasChild(email)) {
                            val user = it.child(email).getValue(User::class.java)
                            if (user!!.password == pswrd) {
                                helper.deleteUser()
                                helper.insertUser(user)
                                startActivity(Intent(this, MainActivity::class.java))
                            } else {
                                makeToast(this, "WrongPswrd")
                            }
                        } else {
                            makeToast(this, "NotFindMakeNew")
                            val user = User(email, "", "", pswrd)
                            addNewUser(user)
                            helper.deleteUser()
                            helper.insertUser(user)
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                    }
                )
            }
        }
    }
}

