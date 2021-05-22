package com.example.applicationforconferencemisis.Data.Firebase

import android.content.Context
import com.example.applicationforconferencemisis.Data.Firebase.Callbacks.*
import com.example.applicationforconferencemisis.Data.Models.*
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.makeToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList

lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference

const val NODE_USERS = "Users"
const val NODE_CONFERENCES = "Conferences"
const val NODE_PERSONAL_CHATS = "PersonalChats"
const val NODE_MESSAGES = "Messages"
const val NODE_GROUP_CONFERENCES = "GroupConferences"
const val NODE_GROUP_MESSAGE = "GroupMessage"


fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
}

fun addNewUser(user: User) {
    initFirebase()
    REF_DATABASE_ROOT.child(NODE_USERS).child(user.username).setValue(user)
}

fun addNewConference(conference: Conference) {
    initFirebase()
    REF_DATABASE_ROOT.child(NODE_CONFERENCES).child(conference.conferenceId).setValue(conference)
}

fun addConferenceToSchedule(conference: Conference, context: Context) {
    initFirebase()
    val helper = SQLiteHelper(context)
    val user = helper.getUser()
    REF_DATABASE_ROOT.child(NODE_USERS)
        .child(user.username)
        .child(NODE_GROUP_CONFERENCES)
        .child(conference.conferenceId)
        .setValue(conference)
}

fun addMessageToDialog(from: String, message: Message, context: Context) {
    initFirebase()
    val helper = SQLiteHelper(context)
    val user = helper.getUser()
    REF_DATABASE_ROOT.child(NODE_USERS)
        .child(user.username)
        .child(NODE_PERSONAL_CHATS)
        .child(from)
        .child(message.date.toString())
        .setValue(message)

    REF_DATABASE_ROOT.child(NODE_USERS)
        .child(from)
        .child(NODE_PERSONAL_CHATS)
        .child(user.username)
        .child(message.date.toString())
        .setValue(message)
}

fun getUserFromFirebase( context: Context, log: String){
    val localDatabaseHelper = SQLiteHelper(context)
    var user = User()
    helperForGetUserFromFirebase(log, object: CallbackForUser {
        override fun onCallback(list: MutableList<User?>) {
            super.onCallback(list)
            user.username = list[0]!!.username
            user.name = list[0]!!.name
            user.password = list[0]!!.password
            user.description = list[0]!!.description
            localDatabaseHelper.insertUser(user)
        }
    })
}

private fun helperForGetUserFromFirebase(login: String, firebaseCallback: CallbackForUser) {
    initFirebase()
    val ref = REF_DATABASE_ROOT.child(NODE_USERS).child(login)
    var listData = ArrayList<User?>()
    ref.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            val new_user = snapshot.getValue(User::class.java)
            listData.add(new_user)
            firebaseCallback.onCallback(listData)
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    })
}

fun getConferenceFromFirebase(context: Context, log: String){
    initFirebase()
    val localDatabaseHelper = SQLiteHelper(context)
    var conference = Conference()
    helperForGetConferenceFromFirebase(log, object: CallbackForConferences{
        override fun onCallback(list: MutableList<Conference?>) {
            super.onCallback(list)
            conference.conferenceId = list[0]!!.conferenceId
            conference.name = list[0]!!.name
            conference.theme = list[0]!!.theme
            conference.date = list[0]!!.date
            conference.speakers = list[0]!!.speakers
            localDatabaseHelper.insertConference(conference)
        }
    })
}

private fun helperForGetConferenceFromFirebase(login: String, firebaseCallback: CallbackForConferences) {
    initFirebase()
    val ref = REF_DATABASE_ROOT.child(NODE_USERS).child(login)
    var listData = ArrayList<Conference?>()
    ref.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            val new_user = snapshot.getValue(Conference::class.java)
            listData.add(new_user)
            firebaseCallback.onCallback(listData)
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    })
}

fun getGroupConferenceFromFirebase( context: Context, log: String){
    initFirebase()
    val localDatabaseHelper = SQLiteHelper(context)
    var groupConferences = GroupConferences()
    helperForGetGroupConferenceFromFirebase(log, object: CallbackForGroupConferences {
        override fun onCallback(list: MutableList<String?>) {
            super.onCallback(list)
            for (id in list)
                if (!id.isNullOrEmpty())
                    localDatabaseHelper.insertConferenceToSchedule(id)
        }
    })
}

private fun helperForGetGroupConferenceFromFirebase(login: String, firebaseCallback: CallbackForGroupConferences) {
    initFirebase()
    val ref = REF_DATABASE_ROOT.child(NODE_USERS).child(login).child(NODE_GROUP_CONFERENCES)
    var listData = ArrayList<String?>()
    ref.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            for (conference in snapshot.children){
                val idConference = conference.getValue(String::class.java)
                listData.add(idConference)
            }
            firebaseCallback.onCallback(listData)
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    })
}

fun sendMessage(message: String, receivingUserId: String, context: Context, function: () -> Unit) {
    initFirebase()
    val localDatabaseHelper = SQLiteHelper(context)
    val user = localDatabaseHelper.getUser()
    val mess = Message("text", "date", "fromUser")
    val redDialogUser = "$NODE_MESSAGES/${user.username}/$receivingUserId"
    val redDialogReceivingUser = "$NODE_MESSAGES/$receivingUserId/${user.username}"
    val messageKey = REF_DATABASE_ROOT.child(redDialogUser).push().key

    val mapMessage = hashMapOf<String,Any>()
    mapMessage[mess.fromUser] = user.username
    mapMessage[mess.text] = message
    mapMessage[mess.date.toString()] = ServerValue.TIMESTAMP

    val mapDialog = hashMapOf<String,Any>()
    mapDialog["$redDialogUser/$messageKey"] = mapMessage
    mapDialog["$redDialogReceivingUser/$messageKey"] = mapMessage
    REF_DATABASE_ROOT
        .updateChildren(mapDialog)
        .addOnSuccessListener { function() }
        .addOnFailureListener { makeToast(context,"aye") }
}

fun sendGroupMessage(message: String, context: Context, function: () -> Unit) {
    initFirebase()
    val localDatabaseHelper = SQLiteHelper(context)
    val user = localDatabaseHelper.getUser()
    val mess = Message("text", "date", "fromUser")
    val redDialogUser = "$NODE_GROUP_MESSAGE/"
    val messageKey = REF_DATABASE_ROOT.child(redDialogUser).push().key

    val mapMessage = hashMapOf<String,Any>()
    mapMessage[mess.fromUser] = user.username
    mapMessage[mess.text] = message
    mapMessage[mess.date.toString()] = ServerValue.TIMESTAMP

    val mapDialog = hashMapOf<String,Any>()
    mapDialog["$redDialogUser/$messageKey"] = mapMessage
    REF_DATABASE_ROOT
        .updateChildren(mapDialog)
        .addOnSuccessListener { function() }
        .addOnFailureListener { makeToast(context,"aye") }
}

fun addNewDialog(withUserLogin:String, context: Context){
    val helper = SQLiteHelper(context)
    initFirebase()
    REF_DATABASE_ROOT.child(NODE_USERS).child(helper.getUser().username).child(NODE_PERSONAL_CHATS).child(withUserLogin).setValue(withUserLogin)
    REF_DATABASE_ROOT.child(NODE_USERS).child(withUserLogin).child(NODE_PERSONAL_CHATS).child(helper.getUser().username).setValue(helper.getUser().username)
}

fun getUserContactsFromFirebase(context: Context, log: String){
    initFirebase()
    val localDatabaseHelper = SQLiteHelper(context)
    helperForGetUserContactsFromFirebase(log, object: CallbackForGetContacts{
        override fun onCallback(list: MutableList<Contacts?>) {
            super.onCallback(list)
            for(contact in list){
                localDatabaseHelper.insertContactsToContacts(contact!!.usersName)
            }
        }
    })
}

private fun helperForGetUserContactsFromFirebase(login: String, firebaseCallback: CallbackForGetContacts) {
    initFirebase()
    val ref = REF_DATABASE_ROOT.child(NODE_USERS).child(login).child(NODE_PERSONAL_CHATS)
    var listData = ArrayList<Contacts?>()
    ref.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            for (contact in snapshot.children){
                val IDcontact = Contacts(contact.getValue(String::class.java)!!,"","","")
                listData.add(IDcontact)
            }
            firebaseCallback.onCallback(listData)
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    })
}











