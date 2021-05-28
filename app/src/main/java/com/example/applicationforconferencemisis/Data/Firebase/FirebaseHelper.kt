package com.example.applicationforconferencemisis.Data.Firebase

import android.content.Context
import android.os.storage.StorageManager
import com.example.applicationforconferencemisis.Data.Firebase.Callbacks.*
import com.example.applicationforconferencemisis.Data.Models.*
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.makeToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.ArrayList

lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
//lateinit var helper: SQLiteHelper

const val NODE_USERS = "Users"
const val NODE_CONFERENCES = "Conferences"
const val NODE_PERSONAL_CHATS = "PersonalChats"
const val NODE_MESSAGES = "Messages"
const val NODE_GROUP_CONFERENCES = "GroupConferences"
const val NODE_GROUP_MESSAGE = "GroupMessage"
const val FOLDER_PROFILE_IMAGE = "profile_image"
const val NODE_PERSONAL_SCHEDULE = "PersonalSchedule"
const val PHOTO_URL = "photoUrl"
const val NODE_NOTE = "Note"
const val NODE_SESSIONS = "Sessions"
const val NODE_WORKSHOPS = "Workshops"


fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
}

fun addNewUser(user: User) {
    initFirebase()
    REF_DATABASE_ROOT.child(NODE_USERS).child(user.username).setValue(user)
}

fun getGroupConferenceFromFirebase( context: Context, log: String){
    initFirebase()
    val localDatabaseHelper = SQLiteHelper(context)
    var groupConferences = GroupConferences()
    helperForGetGroupConferenceFromFirebase(log, object: CallbackForGroupConferences {
        override fun onCallback(list: MutableList<Triple<String?,String?,String?>>) {
            super.onCallback(list)
            for (conf in list)
                if (!conf.first.isNullOrEmpty())
                    localDatabaseHelper.insertConferenceToSchedule(conf.first!!,conf.second!!,conf.third!!)
        }
    })
}

private fun helperForGetGroupConferenceFromFirebase(login: String, firebaseCallback: CallbackForGroupConferences) {
    initFirebase()
    val ref = REF_DATABASE_ROOT.child(NODE_USERS).child(login).child(NODE_PERSONAL_SCHEDULE)
    var listData = ArrayList<Triple<String?,String?,String?>>()
    ref.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            for (date in snapshot.children){
                val a = date.key
                for (type in date.children){
                    val b = type.key
                    for (confer in type.children){
                        val idConf = confer.key
                        val conf = Triple(idConf,a,b)
                        listData.add(conf)
                    }
                }
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
        .addOnFailureListener {  }
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
        .addOnFailureListener {}
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

fun addInfoForUser(user:User){
    initFirebase()
    val ref = REF_DATABASE_ROOT.child(NODE_USERS).child(user.username).setValue(user)
}













