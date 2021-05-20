package com.example.applicationforconferencemisis.Data.Firebase.Callbacks

import com.example.applicationforconferencemisis.Data.Models.User

interface CallbackForUser {
    fun onCallback(list: MutableList<User?>){

    }
}