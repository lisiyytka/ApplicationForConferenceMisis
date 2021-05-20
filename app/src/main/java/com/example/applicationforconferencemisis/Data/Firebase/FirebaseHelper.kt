package com.example.applicationforconferencemisis.Data.Firebase

import android.content.Context
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.Message
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.makeToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference

const val NODE_USERS = "Users"
const val NODE_CONFERENCES = "Conferences"
const val NODE_PERSONAL_CHATS = "PersonalChats"
const val NODE_MESSAGES = "Messages"
const val NODE_GROUP_CONFERENCES = "GroupConferences"


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
        .child(message.date)
        .setValue(message)

    REF_DATABASE_ROOT.child(NODE_USERS)
        .child(from)
        .child(NODE_PERSONAL_CHATS)
        .child(user.username)
        .child(message.date)
        .setValue(message)
}

//suspend fun helperForGetUser(context: Context, login: String): User {
//    return suspendCoroutine { continuation ->
//        getUser(login, object : FirebaseCallback {
//            override fun onCallback(list: MutableList<User?>) {
//                super.onCallback(list)
//                continuation.resume(list[0]!!)
//            }
//        })
//    }
//}
//
//fun getUser(login: String, firebaseCallback: FirebaseCallback) {
//    initFirebase()
//    val ref = REF_DATABASE_ROOT.child(NODE_USERS).child(login)
//    var listData = ArrayList<User?>()
//    val listener = object : ValueEventListener {
//        override fun onCancelled(databaseError: DatabaseError) {
//        }
//
//        override fun onDataChange(dataSnapshot: DataSnapshot) {
//            val new_user = dataSnapshot.getValue(User::class.java)
//            listData.add(new_user)
//            firebaseCallback.onCallback(listData)
//        }
//    }
//    ref.addValueEventListener(listener)
//}


fun getUserFromFirebase( context: Context, log: String){
    val localDatabaseHelper = SQLiteHelper(context)
    var user = User()
    helperForGetUserFromFirebase(log, object: FirebaseCallback{
        override fun onCallback(list: MutableList<User?>) {
            super.onCallback(list)
            user.username = list[0]!!.username
            user.name = list[0]!!.name
            user.mail = list[0]!!.mail
            user.description = list[0]!!.description
            localDatabaseHelper.insertUser(user)
        }
    })
}

fun helperForGetUserFromFirebase(login: String, firebaseCallback: FirebaseCallback) {
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
    val localDatabaseHelper = SQLiteHelper(context)
    var user = User()
    helperForGetConferenceFromFirebase(log, object: FirebaseCallback{
        override fun onCallback(list: MutableList<User?>) {
            super.onCallback(list)
        }
    })
}

fun helperForGetConferenceFromFirebase(login: String, firebaseCallback: FirebaseCallback) {
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







