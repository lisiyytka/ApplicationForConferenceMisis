package com.example.applicationforconferencemisis.Fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Activities.MainActivity
import com.example.applicationforconferencemisis.Activities.RegisterActivity
import com.example.applicationforconferencemisis.Activities.lastFragment
import com.example.applicationforconferencemisis.Data.Firebase.*
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.downloadAndSetImage
import com.example.applicationforconferencemisis.makeToast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onResume() {
        super.onResume()
        val changeUserPhoto = view!!.findViewById<ImageView>(R.id.change_user_photo_pencil)
        val userPhoto = view!!.findViewById<ImageView>(R.id.change_user_photo)
        val resume = view!!.findViewById<ImageButton>(R.id.next_btn)
        val username = view!!.findViewById<EditText>(R.id.user_name_surname)
        val description = view!!.findViewById<EditText>(R.id.inf_about_user)
        val helper = SQLiteHelper(context!!)
        changeUserPhoto.setOnClickListener {
            changePhoto()
        }
        val user = helper.getUser()
        resume.setOnClickListener {
            if (username.text.toString().isEmpty())
                makeToast(context!!, "Fill the fields")
            else {
                REF_DATABASE_ROOT.child(NODE_USERS).child(user.username).addListenerForSingleValueEvent(
                    AppValueEventListener {
                        val userFromFire = it.getValue(User::class.java)
                        user.description = description.text.toString()
                        user.name = username.text.toString()
                        user.photoUrl = userFromFire!!.photoUrl
                        helper.deleteUser()
                        helper.insertUser(user)
                        addInfoForUser(user)
                        lastFragment = RegisterFragment()
                        startActivity(Intent(context!!, MainActivity::class.java))
                    }
                )



//                user.description = description.text.toString()
//                user.name = username.text.toString()
//                helper.deleteUser()
//                helper.insertUser(user)
//                addInfoForUser(user)
//                lastFragment = RegisterFragment()
//                startActivity(Intent(context!!, MainActivity::class.java))
            }
        }
        if (lastFragment != null) {
            initFirebase()
            REF_DATABASE_ROOT.child(NODE_USERS).child(helper.getUser().username)
                .addListenerForSingleValueEvent(
                    AppValueEventListener {
                        val user = it.getValue(User::class.java)
                        userPhoto.downloadAndSetImage(user!!.photoUrl)
                    }
                )
            username.setText(helper.getUser().name)
            description.setText(helper.getUser().description)
        }
    }

    private fun changePhoto() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .setCropShape(CropImageView.CropShape.RECTANGLE)
            .start(activity as RegisterActivity)
    }
}