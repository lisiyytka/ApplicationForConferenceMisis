package com.example.applicationforconferencemisis.Data.Models


class User {

    var username:String = ""
    var name: String = ""
    var description: String = ""
    var password: String = ""
    var photoUrl: String =""

    constructor()

    constructor(username: String, name: String, description: String, password: String, photoUrl: String
    ) {
        this.username = username
        this.password = password
        this.name = name
        this.description = description
        this.photoUrl = photoUrl
    }


}