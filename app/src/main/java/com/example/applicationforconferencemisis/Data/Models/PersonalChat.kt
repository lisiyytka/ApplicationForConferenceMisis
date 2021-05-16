package com.example.applicationforconferencemisis.Data.Models

class PersonalChat {
    var userIdFirst:String = ""
    var userIdSecond:String = ""

    constructor()
    constructor(userIdFirst: String, userIdSecond: String) {
        this.userIdFirst = userIdFirst
        this.userIdSecond = userIdSecond
    }

}