package com.example.applicationforconferencemisis.Data.Models

class GlobalMessages {
    var fromUser:String = ""
    var date:String = ""
    var text:String = ""

    constructor()

    constructor(fromUser: String, date: String, text: String) {
        this.fromUser = fromUser
        this.date = date
        this.text = text
    }
}