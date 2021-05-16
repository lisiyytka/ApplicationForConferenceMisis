package com.example.applicationforconferencemisis.Data.Models

class Conference {
    var conferenceId:String = ""
    var name:String = ""
    var theme:String = ""
    var date:String = ""
    var speakers:String = ""

    constructor()

    constructor(conferenceId: String, name: String, theme: String, date: String, speakers: String) {
        this.conferenceId = conferenceId
        this.name = name
        this.theme = theme
        this.date = date
        this.speakers = speakers
    }

}