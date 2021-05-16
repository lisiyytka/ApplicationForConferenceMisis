package com.example.applicationforconferencemisis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Utilits {
    fun makeToast(context: Context,arg:String){
        Toast.makeText(context,arg, Toast.LENGTH_LONG).show()
    }
}