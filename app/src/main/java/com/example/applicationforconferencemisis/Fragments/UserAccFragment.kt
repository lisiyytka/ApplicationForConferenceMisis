package com.example.applicationforconferencemisis.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Activities.LoginActivity
import com.example.applicationforconferencemisis.Activities.fragmentName
import com.example.applicationforconferencemisis.Activities.lastBtnId
import com.example.applicationforconferencemisis.Activities.lastFragment
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_USERS
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Firebase.initFirebase
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.downloadAndSetImage
import com.example.applicationforconferencemisis.replaceFragment

class UserAccFragment(userOnButton:String): Fragment() {

    private val user = userOnButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        REF_DATABASE_ROOT.child(NODE_USERS).child(user).addListenerForSingleValueEvent(
            AppValueEventListener{
                val userFromFire = it.getValue(User::class.java)
                fragmentName!!.text = userFromFire!!.name
            }
        )

        return inflater.inflate(R.layout.fragment_user_acc, container, false)
    }


    override fun onResume() {
        super.onResume()
        val sendMessage = view!!.findViewById<ImageView>(R.id.send_msg_btn)
        val imgProfile = view!!.findViewById<ImageView>(R.id.img_profile)
        val aboutUser = view!!.findViewById<TextView>(R.id.about_user)
        val layRole = view!!.findViewById<LinearLayout>(R.id.lay_role)
        val helper = SQLiteHelper(context!!)
        layRole.visibility = View.GONE
        initFirebase()
        REF_DATABASE_ROOT.child(NODE_USERS).child(user).addListenerForSingleValueEvent(
            AppValueEventListener{
                val userFromFire = it.getValue(User::class.java)
                if (userFromFire!!.role == "Speaker") {
                    layRole.visibility = View.VISIBLE
                }
                imgProfile.downloadAndSetImage(userFromFire.photoUrl)
                aboutUser.text = userFromFire.description
            }
        )


        sendMessage.setOnClickListener {
            lastFragment = null
            replaceFragment(SingleChatFragment(user))
        }
    }
}