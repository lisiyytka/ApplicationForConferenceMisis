package com.example.applicationforconferencemisis.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import com.example.applicationforconferencemisis.Data.Firebase.*
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.makeToast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initFirebase()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val helper = SQLiteHelper(this)
//        val parser = Parserrr()
//        val path = "${Environment.getExternalStorageDirectory().absolutePath}/users.xlsx"
//        makeToast(this,path)
//        parser.parse(path, "Лист1")
        val buttonForLogin = findViewById<ImageButton>(R.id.buttonForLogin)
        val editTextEmail = findViewById<EditText>(R.id.edit_text_email)
        val editTextPassword = findViewById<EditText>(R.id.edit_text_password)
        val localUser = helper.getUser()
        if (localUser.username!="")
            if (localUser.name=="")
                startActivity(Intent(this, RegisterActivity::class.java))
            else{
                helper.deleteGroupConference()
                helper.deleteGroupContacts()
                getGroupConferenceFromFirebase(this, localUser.username)
                getUserContactsFromFirebase(this, localUser.username)
                startActivity(Intent(this, MainActivity::class.java))
            }


        //ПОДУМОЙ, КАК ЧАСТО ОБНОВЛЯТЬ ДАННЫЕ(КАКАТЬ)?

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
                                helper.deleteAllPersonalData()
                                helper.insertUser(user)
                                getGroupConferenceFromFirebase(this, user.username)
                                getUserContactsFromFirebase(this, user.username)
                                startActivity(Intent(this, MainActivity::class.java))
                            } else {
                                makeToast(this, "Wrong Password")
                            }
                        } else {
                            makeToast(this, "NotFindMakeNew")
                            val user = User(email, "", "", pswrd, "", "")
                            addNewUser(user)
                            helper.deleteAllPersonalData()
                            helper.insertUser(user)
                            startActivity(Intent(this, RegisterActivity::class.java))
                        }
                    }
                )
            }
        }
    }
}