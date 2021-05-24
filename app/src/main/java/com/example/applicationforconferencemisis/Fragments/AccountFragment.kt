package com.example.applicationforconferencemisis.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Activities.LoginActivity
import com.example.applicationforconferencemisis.Activities.MainActivity
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R

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
        val helper = SQLiteHelper(context!!)

        logout.setOnClickListener {
            helper.deleteAllPersonalData()
            startActivity(Intent(context!!, LoginActivity::class.java))
        }
    }
}