package com.example.applicationforconferencemisis.Data.Models

import android.widget.ImageView
import android.widget.TextView

class Contacts {

//    var usersImg: String = ""
    var usersName: String = ""
    var lastMessage: String = ""
    var time: String = ""
    var numberOfUnreadMsg: String = ""

    constructor()

    constructor(
//        usersImg: String,
        usersName: String,
        lastMessage: String,
        time: String,
        numberOfUnreadMsg: String
    ) {
//        this.usersImg = usersImg
        this.usersName = usersName
        this.lastMessage = lastMessage
        this.time = time
        this.numberOfUnreadMsg = numberOfUnreadMsg
    }
}