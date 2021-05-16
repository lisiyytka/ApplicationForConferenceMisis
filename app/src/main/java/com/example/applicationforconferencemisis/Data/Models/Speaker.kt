package com.example.applicationforconferencemisis.Data.Models

class Speaker {
    var speakerId: String = ""
    var name: String = ""
    var rank: String = ""
    var description: String = ""

    constructor()

    constructor(speakerId: String, name: String, rank: String, description: String) {
        this.speakerId = speakerId
        this.name = name
        this.rank = rank
        this.description = description
    }
}