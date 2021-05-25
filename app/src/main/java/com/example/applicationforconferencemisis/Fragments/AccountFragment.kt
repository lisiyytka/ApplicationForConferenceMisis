package com.example.applicationforconferencemisis.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Activities.LoginActivity
import com.example.applicationforconferencemisis.Activities.MainActivity
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

class AccountFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acc, container, false)
    }

    override fun onResume() {
        super.onResume()
        val logout = view!!.findViewById<ImageView>(R.id.logout)
        val edit = view!!.findViewById<ImageView>(R.id.edit)
        val imgProfile = view!!.findViewById<ImageView>(R.id.img_profile)
        val aboutUser = view!!.findViewById<TextView>(R.id.about_user)
        val helper = SQLiteHelper(context!!)
        initFirebase()
        REF_DATABASE_ROOT.child(NODE_USERS).child(helper.getUser().username).addListenerForSingleValueEvent(
            AppValueEventListener{
                val user = it.getValue(User::class.java)
                imgProfile.downloadAndSetImage(user!!.photoUrl)
            }
        )
        aboutUser.text = helper.getUser().description

        logout.setOnClickListener {
            helper.deleteAllPersonalData()
            startActivity(Intent(context!!, LoginActivity::class.java))
        }

        edit.setOnClickListener {
            lastFragment = AccountFragment()
            lastBtnId = R.id.account_btn
            replaceFragment(EditFragment())
        }
    }
}