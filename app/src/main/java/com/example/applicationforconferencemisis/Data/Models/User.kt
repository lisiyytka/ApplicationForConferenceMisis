package com.example.applicationforconferencemisis.Data.Models


class User {

    var username: String = ""
    var name: String = ""
    var description: String = ""
    var mail: String = ""
    var password: String = ""
    var photoUrl: String = ""
    var role: String = ""

    constructor()

    constructor(
        username: String,
        name: String,
        description: String,
        password: String,
        photoUrl: String,
        mail: String,
        role: String
    ) {
        this.username = username
        this.password = password
        this.name = name
        this.description = description
        this.photoUrl = photoUrl
        this.mail = mail
        this.role = role
    }

    constructor(mail: String, name: String, username: String, password: String) {
        this.username = username
        this.name = name
        this.mail = mail
        this.password = password
        this.role = "DefaultUser"
    }

    constructor(mail: String, name: String, username: String, password: String, role: String) {
        this.username = username
        this.name = name
        this.mail = mail
        this.password = password
        this.role = role
    }

    constructor(
        username: String,
        name: String,
        description: String,
        mail: String,
        password: String,
        photoUrl: String
    ) {
        this.username = username
        this.name = name
        this.description = description
        this.mail = mail
        this.password = password
        this.photoUrl = photoUrl
        this.role = "DefaultUser"
    }


}