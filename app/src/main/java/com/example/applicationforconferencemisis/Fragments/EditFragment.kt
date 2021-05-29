package com.example.applicationforconferencemisis.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Activities.DifferentActivity
import com.example.applicationforconferencemisis.Activities.RegisterActivity
import com.example.applicationforconferencemisis.Activities.lastFragment
import com.example.applicationforconferencemisis.Data.Firebase.*
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.downloadAndSetImage
import com.example.applicationforconferencemisis.makeToast
import com.example.applicationforconferencemisis.replaceFragment
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class EditFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onResume() {
        super.onResume()
        val changeUserPhoto = view!!.findViewById<ImageView>(R.id.change_user_photo_pencil)
        val userPhoto = view!!.findViewById<ImageView>(R.id.change_user_photo)
        val username = view!!.findViewById<EditText>(R.id.user_name_surname)
        val description = view!!.findViewById<EditText>(R.id.inf_about_user)
        val nextBtn = view!!.findViewById<ImageButton>(R.id.next_btn)
        val helper = SQLiteHelper(context!!)
        changeUserPhoto.setOnClickListener {
            changePhoto()
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

        nextBtn.setOnClickListener {
            initFirebase()
            REF_DATABASE_ROOT.child(NODE_USERS).child(helper.getUser().username).child(helper.getUser().photoUrl).addListenerForSingleValueEvent(
                AppValueEventListener{
                    val user = it.getValue(User::class.java)
                    userPhoto.downloadAndSetImage(user!!.photoUrl)
                    user.description = description.text.toString()
                    user.name = username.text.toString()
                    helper.deleteUser()
                    helper.insertUser(user)
                    addInfoForUser(user)
                    for (i in helper.getContacts()) {
                        addNewDialog(i.usersName,context!!)
                    }
                    lastFragment = AccountFragment()
                    replaceFragment(AccountFragment())
                }
            )
        }
    }

    private fun changePhoto() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .setCropShape(CropImageView.CropShape.RECTANGLE)
            .start(activity as DifferentActivity)
    }
}