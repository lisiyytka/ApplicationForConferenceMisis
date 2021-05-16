package com.example.applicationforconferencemisis.Data.Models

class Dialogue {
    var fromUser:String = ""
    var toUser:String = ""
    var lastMessage:String = ""

    constructor()
    constructor(fromUser: String, toUser: String, lastMessage: String) {
        this.fromUser = fromUser
        this.toUser = toUser
        this.lastMessage = lastMessage
    }

}