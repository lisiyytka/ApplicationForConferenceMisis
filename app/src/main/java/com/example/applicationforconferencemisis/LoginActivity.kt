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
            when (findUser(email, pswrd)) {
                USERFIND -> startActivity(Intent(this, MainActivity::class.java))
                WRONGPSWD -> makeToast(this, "Wrong password")
                INCORRECTDATA -> makeToast(this, "Fill in the fields!")
                USERISNOTFIND -> {
                    helper.deleteUser()
                    val user = User(email,"","",pswrd)
                    helper.insertUser(user)
                    addNewUser(user)
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }
    }

    fun findUser(emailEntrance: String, pswdEntrance: String): StatusLogin {
        if (emailEntrance.isEmpty() || pswdEntrance.isEmpty()) {
            return INCORRECTDATA
        }
        initFirebase()
        val ref = REF_DATABASE_ROOT.child(NODE_USERS)
        var result = USERISNOTFIND
        val helper = SQLiteHelper(this)
        ref.addListenerForSingleValueEvent(
            AppValueEventListener {
                if (it.hasChild(emailEntrance)) {
                    REF_DATABASE_ROOT.child(NODE_USERS).child(emailEntrance).addListenerForSingleValueEvent(
                        AppValueEventListener {
                            val user = it.getValue(User::class.java)
                            if (user!!.password == pswdEntrance) {
                                result = USERFIND
                                helper.deleteUser()
                                helper.insertUser(user)
                            }
                            else
                                result = WRONGPSWD
                            //Я не знаю почему он вместо того чтобы вывести мне неправильный пароль просто заменяет его
                        }
                    )
                }
            }
        )
        return result
    }
}

