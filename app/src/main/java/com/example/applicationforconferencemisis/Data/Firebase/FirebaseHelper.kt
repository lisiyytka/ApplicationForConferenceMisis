package com.example.applicationforconferencemisis.Data.Firebase

import android.content.Context
import com.example.applicationforconferencemisis.Data.Firebase.Callbacks.CallbackForConferences
import com.example.applicationforconferencemisis.Data.Firebase.Callbacks.CallbackForGroupConferences
import com.example.applicationforconferencemisis.Data.Firebase.Callbacks.CallbackForUser
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.GroupConferences
import com.example.applicationforconferencemisis.Data.Models.Message
import com.example.applicationforconferencemisis.Data.Models.User
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

fun helperForGetUserFromFirebase(login: String, firebaseCallback: CallbackForUser) {
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

fun helperForGetConferenceFromFirebase(login: String, firebaseCallback: CallbackForConferences) {
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
    val localDatabaseHelper = SQLiteHelper(context)
    var groupConferences = GroupConferences()
    helperForGetGroupConferenceFromFirebase(log, object: CallbackForGroupConferences {
        override fun onCallback(list: MutableList<GroupConferences?>) {
            super.onCallback(list)
            groupConferences.conferencens = list[0]!!.conferencens
            localDatabaseHelper.insertConferenceToSchedule(groupConferences.conferencens)
        }
    })
}

fun helperForGetGroupConferenceFromFirebase(login: String, firebaseCallback: CallbackForGroupConferences) {
    initFirebase()
    val ref = REF_DATABASE_ROOT.child(NODE_USERS).child(login)
    var listData = ArrayList<GroupConferences?>()
    ref.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            val new_user = snapshot.getValue(GroupConferences::class.java)
            listData.add(new_user)
            firebaseCallback.onCallback(listData)
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    })
}

fun sendMessage(message: String, receivingUserId: String, context: Context, function: () -> Unit) {
    val localDatabaseHelper = SQLiteHelper(context)
    val user = localDatabaseHelper.getUser()
    val message = Message()
    val redDialogUser = "$NODE_MESSAGES/${user.username}/$receivingUserId"
    val redDialogReceivingUser = "$NODE_MESSAGES/$receivingUserId/${user.username}"
    val messageKey = REF_DATABASE_ROOT.child(redDialogUser).push().key

    val mapMessage = hashMapOf<String,Any>()
    mapMessage[message.fromUser] = user.username
    mapMessage[message.text] = message
    mapMessage[message.date] = ServerValue.TIMESTAMP

    val mapDialog = hashMapOf<String,Any>()
    mapDialog["$redDialogUser/$messageKey"] = mapMessage
    mapDialog["$redDialogReceivingUser/$messageKey"] = mapMessage
    REF_DATABASE_ROOT
        .updateChildren(mapDialog)
        .addOnSuccessListener { function() }
        .addOnFailureListener { makeToast(context,"aye") }
}







