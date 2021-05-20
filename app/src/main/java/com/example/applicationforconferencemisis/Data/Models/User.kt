package com.example.applicationforconferencemisis.Data.Models


class User {

    var username:String = ""
    var name: String = ""
    var description: String = ""
    var password: String = ""

    constructor()

    constructor(username: String, name: String, description: String, password: String) {
        this.username = username
        this.password = password
        this.name = name
        this.description = description
    }


}