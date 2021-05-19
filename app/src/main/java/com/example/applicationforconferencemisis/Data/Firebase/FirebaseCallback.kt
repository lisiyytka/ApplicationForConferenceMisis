package com.example.applicationforconferencemisis.Data.Firebase

import com.example.applicationforconferencemisis.Data.Models.User

interface FirebaseCallback {
    fun onCallback(list: MutableList<User?>){

    }
}