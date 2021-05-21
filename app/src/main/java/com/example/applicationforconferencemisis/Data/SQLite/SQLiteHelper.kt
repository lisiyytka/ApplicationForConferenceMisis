package com.example.applicationforconferencemisis.Data.SQLite



import android.accessibilityservice.AccessibilityService
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.AdapterView
import android.widget.Toast
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.Contacts
import com.example.applicationforconferencemisis.Data.Models.User
import java.security.AccessControlContext
import javax.security.auth.callback.Callback

val DATABASE_NAME = "MyDB"
val TABLE_NAME_USERS = "Users"
val TABLE_NAME_GROUP_CONFERENCES = "GroupConferences"
val TABLE_NAME_CONFERENCES = "Conferences"
val USERS_COL_ID = "Id"
val USERS_COL_NAME = "Name"
val USERS_COL_PASSWORD= "Mail"
val USERS_COL_DESCRIPTION= "Description"
val GROUP_CONFERENCES_COL_CONFERENCES = "Conferences"
val CONFERENCES_COL_CONFERENCE_ID = "ConferenceId"
val CONFERENCES_COL_NAME = "Name"
val CONFERENCES_COL_THEME = "Theme"
val CONFERENCES_COL_DATE = "Date"
val CONFERENCES_COL_SPEAKERS = "Speakers"
val GROUP_CONTACTS_COL_CONTACTS = "Contacts"
val TABLE_NAME_GROUP_CONTACTS = "GroupContacts"

class SQLiteHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1) {

    lateinit var a:String
    override fun onCreate(db: SQLiteDatabase?) {

        val createTableUsers = "CREATE TABLE " + TABLE_NAME_USERS + " (" +
                USERS_COL_ID + " VARCHAR(256) PRIMARY KEY, " +
                USERS_COL_NAME+ " VARCHAR(256), " +
                USERS_COL_PASSWORD + " VARCHAR(256), " +
                USERS_COL_DESCRIPTION + " VARCHAR(256))"
        db?.execSQL(createTableUsers)

        val createTableGroupConferences = "CREATE TABLE " + TABLE_NAME_GROUP_CONFERENCES + " (" +
                GROUP_CONFERENCES_COL_CONFERENCES + " VARCHAR(256))"
        db?.execSQL(createTableGroupConferences)

        val createTableConferences = "CREATE TABLE " + TABLE_NAME_CONFERENCES + " (" +
                CONFERENCES_COL_CONFERENCE_ID + " VARCHAR(256) PRIMARY KEY, " +
                CONFERENCES_COL_NAME + " VARCHAR(256), " +
                CONFERENCES_COL_THEME + " VARCHAR(256), " +
                CONFERENCES_COL_DATE + " VARCHAR(256), " +
                CONFERENCES_COL_SPEAKERS + " VARCHAR(256))"
        db?.execSQL(createTableConferences)

        val createTableGroupContacts = "CREATE TABLE " + TABLE_NAME_GROUP_CONTACTS + " (" +
                GROUP_CONTACTS_COL_CONTACTS + " VARCHAR(256))"
        db?.execSQL(createTableGroupContacts)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun insertUser(user: User) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(USERS_COL_NAME, user.name)
        cv.put(USERS_COL_ID, user.username)
        cv.put(USERS_COL_PASSWORD, user.password)
        cv.put(USERS_COL_DESCRIPTION, user.description)
        db.insert(TABLE_NAME_USERS, null, cv)
    }

    fun insertConferenceToSchedule(idConference: String){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(GROUP_CONFERENCES_COL_CONFERENCES, idConference)
        db.insert(TABLE_NAME_GROUP_CONFERENCES, null, cv)
    }

    fun insertContactsToContacts(idContacts: String){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(GROUP_CONTACTS_COL_CONTACTS, idContacts)
        db.insert(TABLE_NAME_GROUP_CONTACTS, null, cv)
    }

    fun getContacts():List<Contacts>{

        val listContactsNames: MutableList<Contacts> = ArrayList()
        var db = this.readableDatabase
        var query = "Select * from $TABLE_NAME_GROUP_CONTACTS"
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                val contact = Contacts()
                contact.usersName = result.getString(result.getColumnIndex(GROUP_CONTACTS_COL_CONTACTS)).toString()
                listContactsNames.add(contact)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return listContactsNames
    }

    fun insertConference(conference: Conference){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(CONFERENCES_COL_CONFERENCE_ID, conference.conferenceId)
        cv.put(CONFERENCES_COL_DATE, conference.date)
        cv.put(CONFERENCES_COL_NAME, conference.name)
        cv.put(CONFERENCES_COL_SPEAKERS, conference.speakers)
        cv.put(CONFERENCES_COL_THEME, conference.theme)
        db.insert(TABLE_NAME_CONFERENCES, null, cv)
    }

    fun getAllConferencesFromSchedule():ArrayList<Conference>{
        val listId: MutableList<String> = ArrayList()
        var db = this.readableDatabase
        var query = "Select * from $TABLE_NAME_GROUP_CONFERENCES"
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                listId.add(result.getString(result.getColumnIndex(GROUP_CONFERENCES_COL_CONFERENCES)).toString())
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        db = this.readableDatabase
        val listConference:ArrayList<Conference> = arrayListOf()
        listId.forEach {
            query = "Select * from $TABLE_NAME_CONFERENCES where $CONFERENCES_COL_CONFERENCE_ID = $it"
            result = db.rawQuery(query, null)
            if (result.moveToFirst()){
                do {
                    val conference = Conference()
                    conference.conferenceId=(result.getString(result.getColumnIndex(CONFERENCES_COL_CONFERENCE_ID)).toString())
                    conference.date=(result.getString(result.getColumnIndex(CONFERENCES_COL_DATE)).toString())
                    conference.name=(result.getString(result.getColumnIndex(CONFERENCES_COL_NAME)).toString())
                    conference.theme=(result.getString(result.getColumnIndex(CONFERENCES_COL_THEME)).toString())
                    conference.speakers=(result.getString(result.getColumnIndex(CONFERENCES_COL_SPEAKERS)).toString())
                    listConference.add(conference)
                } while (result.moveToNext())
            }
        }
        result.close()
        db.close()
        return listConference
    }

    fun getConference(idConference: String): Conference{
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME_CONFERENCES where $CONFERENCES_COL_CONFERENCE_ID = $idConference"
        val result = db.rawQuery(query, null)
        val conference = Conference()
        if (result.moveToFirst()){
            do {
                conference.speakers=result.getString(result.getColumnIndex(CONFERENCES_COL_SPEAKERS)).toString()
                conference.name=result.getString(result.getColumnIndex(CONFERENCES_COL_NAME)).toString()
                conference.conferenceId=result.getString(result.getColumnIndex(CONFERENCES_COL_CONFERENCE_ID)).toString()
                conference.theme=result.getString(result.getColumnIndex(CONFERENCES_COL_THEME)).toString()
                conference.date=result.getString(result.getColumnIndex(CONFERENCES_COL_DATE)).toString()
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return conference
    }

    fun getAllConferences():List<Conference>{
        val listOfConferences: MutableList<Conference> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME_CONFERENCES"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                val conference = Conference()
                conference.conferenceId = result.getString(result.getColumnIndex(CONFERENCES_COL_CONFERENCE_ID)).toString()
                conference.name = result.getString(result.getColumnIndex(CONFERENCES_COL_NAME)).toString()
                conference.theme = result.getString(result.getColumnIndex(CONFERENCES_COL_THEME)).toString()
                conference.date = result.getString(result.getColumnIndex(CONFERENCES_COL_DATE)).toString()
                conference.speakers = result.getString(result.getColumnIndex(CONFERENCES_COL_SPEAKERS)).toString()
                listOfConferences.add(conference)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return listOfConferences
    }

    fun getUser(): User {
        val list: MutableList<User> = ArrayList()
        val user = User()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME_USERS"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                user.name = result.getString(result.getColumnIndex(USERS_COL_NAME)).toString()
                user.password = result.getString(result.getColumnIndex(USERS_COL_PASSWORD)).toString()
                user.description = result.getString(result.getColumnIndex(USERS_COL_DESCRIPTION)).toString()
                user.username = result.getString(result.getColumnIndex(USERS_COL_ID)).toString()
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return user
    }

    fun deleteUser(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME_USERS,null,null)
        db.close()
    }

    fun deleteGroupContacts(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME_GROUP_CONTACTS,null,null)
        db.close()
    }

    fun deleteGroupConference(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME_GROUP_CONFERENCES,null,null)
        db.close()
    }

    fun deleteAllPersonalData(){
        deleteUser()
        deleteGroupConference()
        deleteGroupContacts()
    }
}