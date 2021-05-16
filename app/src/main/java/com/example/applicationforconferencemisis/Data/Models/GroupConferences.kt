package com.example.applicationforconferencemisis.Data.Models

class GroupConferences {
    var userId:String = ""
    var conferencens:String = ""

    constructor()

    constructor(userId: String, conferencens: String) {
        this.userId = userId
        this.conferencens = conferencens
    }
}