package com.example.applicationforconferencemisis.Data.Models

class User {
    var username:String = ""
    var name: String = ""
    var mail: String = ""
    var description: String = ""

    constructor()

    constructor(username: String, name: String, mail: String, description: String) {
        this.username = username
        this.name = name
        this.mail = mail
        this.description = description
    }


}