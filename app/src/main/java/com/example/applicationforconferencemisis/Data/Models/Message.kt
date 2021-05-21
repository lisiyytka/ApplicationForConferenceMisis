package com.example.applicationforconferencemisis.Data.Models

class Message {
    var text:String = ""
    var date: String = ""
    var fromUser: String = ""
    var toUser: String = ""


    constructor()
    constructor(text: String, date: String, fromUser: String, toUser: String) {
        this.text = text
        this.date = date
        this.fromUser = fromUser
        this.toUser = toUser
    }

}