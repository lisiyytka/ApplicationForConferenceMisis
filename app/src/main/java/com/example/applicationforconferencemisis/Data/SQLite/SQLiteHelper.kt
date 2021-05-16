package com.example.applicationforconferencemisis.Data.SQLite



import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.applicationforconferencemisis.Data.Models.User
import java.security.AccessControlContext
val DATABASE_NAME = "MyDB"
val TABLE_NAME_USERS = "Users"
val TABLE_NAME_GROUP_CONFERENCES = "GroupConferences"
val TABLE_NAME_CONFERENCES = "Conferences"
val USERS_COL_ID = "Id"
val USERS_COL_NAME = "Name"
val USERS_COL_MAIL= "Mail"
val USERS_COL_DESCRIPTION= "Description"
val GROUP_CONFERENCES_COL_USER_ID = "UserId"
val GROUP_CONFERENCES_COL_CONFERENCES = "Conferences"
val CONFERENCES_COL_CONFERENCE_ID = "ConferenceId"
val CONFERENCES_COL_NAME = "Name"
val CONFERENCES_COL_THEME = "Theme"
val CONFERENCES_COL_DATE = "Date"
val CONFERENCES_COL_SPEAKERS = "Speakers"

class SQLiteHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1) {


    override fun onCreate(db: SQLiteDatabase?) {

        val createTableUsers = "CREATE TABLE " + TABLE_NAME_USERS + " (" +
                USERS_COL_ID + " VARCHAR(256) PRIMARY KEY, " +
                USERS_COL_NAME+ " VARCHAR(256), " +
                USERS_COL_MAIL + " VARCHAR(256), " +
                USERS_COL_DESCRIPTION + " VARCHAR(256))"
        db?.execSQL(createTableUsers)

        val createTableGroupConferences = "CREATE TABLE " + TABLE_NAME_GROUP_CONFERENCES + " (" +
                GROUP_CONFERENCES_COL_USER_ID + " VARCHAR(256) PRIMARY KEY, " +
                GROUP_CONFERENCES_COL_CONFERENCES + " VARCHAR(256))"
        db?.execSQL(createTableGroupConferences)

        val createTableConferences = "CREATE TABLE " + TABLE_NAME_CONFERENCES + " (" +
                CONFERENCES_COL_CONFERENCE_ID + " VARCHAR(256) PRIMARY KEY, " +
                CONFERENCES_COL_NAME + " VARCHAR(256), " +
                CONFERENCES_COL_THEME + " VARCHAR(256), " +
                CONFERENCES_COL_DATE + " VARCHAR(256), " +
                CONFERENCES_COL_SPEAKERS + " VARCHAR(256))"
        db?.execSQL(createTableConferences)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertUser(user: User) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(USERS_COL_NAME, user.name)
        cv.put(USERS_COL_ID, user.username)
        cv.put(USERS_COL_MAIL, user.mail)
        cv.put(USERS_COL_DESCRIPTION, user.description)
        db.insert(TABLE_NAME_USERS, null, cv)
//        if (result == -1.toLong())
//            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show()
//        else
//            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun getUser(): User {
        val list: MutableList<User> = ArrayList()
        val user = User()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME_USERS"
        val result = db.rawQuery(query, null)
//        if (result.moveToFirst()) {
//            do {
//                var field = Field()
//                field.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
//                field.balance = result.getString(result.getColumnIndex(COL_BAL)).toDouble()
//                field.category = result.getString(result.getColumnIndex(COL_CATEGORY))
//                field.comment = result.getString(result.getColumnIndex(COL_COMMENT))
//                field.date = result.getString(result.getColumnIndex(COL_DATE))
//                field.loss = result.getString(result.getColumnIndex(COL_LOSS)).toDouble()
//                field.income = result.getString(result.getColumnIndex(COL_INCOME)).toDouble()
//                field.password = result.getString(result.getColumnIndex(COL_PAS))
//                list.add(field)
//            } while (result.moveToNext())
//        }
        if (result.moveToFirst()){
            do {
                user.name = result.getString(result.getColumnIndex(USERS_COL_NAME)).toString()
                user.mail = result.getString(result.getColumnIndex(USERS_COL_MAIL)).toString()
                user.description = result.getString(result.getColumnIndex(USERS_COL_DESCRIPTION)).toString()
                user.username = result.getString(result.getColumnIndex(USERS_COL_ID)).toString()
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return user
    }

    fun deleteData(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME_USERS,null,null)
        db.close()
    }
}