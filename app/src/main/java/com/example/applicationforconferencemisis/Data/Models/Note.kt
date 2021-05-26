package com.example.applicationforconferencemisis.Data.Models

class Note {
    var text: String = ""
    var conference: String = ""


    constructor()
    constructor(text: String, conference: String) {
        this.text = text
        this.conference = conference
    }

}