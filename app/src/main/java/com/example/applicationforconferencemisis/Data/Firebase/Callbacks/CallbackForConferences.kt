package com.example.applicationforconferencemisis.Data.Firebase.Callbacks

import com.example.applicationforconferencemisis.Data.Models.Conference


interface CallbackForConferences {
    fun onCallback(list: MutableList<Conference?>){

    }
}