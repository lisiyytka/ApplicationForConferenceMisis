package com.example.applicationforconferencemisis.Data.Firebase.Callbacks

import com.example.applicationforconferencemisis.Data.Models.Contacts
import com.example.applicationforconferencemisis.Data.Models.ForAll

interface CallbackForGetContacts {
    fun onCallback(list: MutableList<Contacts?>){

    }
}