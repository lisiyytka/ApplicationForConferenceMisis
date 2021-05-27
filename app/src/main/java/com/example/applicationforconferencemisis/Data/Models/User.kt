package com.example.applicationforconferencemisis.Data.Models


class User {

    var username:String = ""
    var name: String = ""
    var description: String = ""
    var mail: String = ""
    var password: String = ""
    var photoUrl: String =""

    constructor()

    constructor(username: String, name: String, description: String, password: String, photoUrl: String, mail:String
    ) {
        this.username = username
        this.password = password
        this.name = name
        this.description = description
        this.photoUrl = photoUrl
        this.mail = mail
    }

    constructor(mail: String, name: String, username: String, password: String) {
        this.username = username
        this.name = name
        this.mail = mail
        this.password = password
    }

}