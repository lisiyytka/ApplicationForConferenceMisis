package com.example.applicationforconferencemisis.Activities

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log.i
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationforconferencemisis.Data.Firebase.*
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.Fragments.RegisterFragment
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.downloadAndSetImage
import com.example.applicationforconferencemisis.makeToast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class RegisterActivity : AppCompatActivity() {
    private val registerFragment = RegisterFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        makeToast(this, "asdasdasd")
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.add(R.id.container_for_register, registerFragment)
        fragmentManager.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val helper = SQLiteHelper(this)
        val changeUserPhotoImage = findViewById<ImageView>(R.id.change_user_photo)
        val nextBtn = findViewById<ImageButton>(R.id.next_btn)
        val username = findViewById<EditText>(R.id.user_name_surname)
        val description = findViewById<EditText>(R.id.inf_about_user)
        val user = helper.getUser()
        nextBtn.setOnClickListener {
            if (username.text.toString().isEmpty())
                makeToast(this, "Fill the field")
            else {
                user.description = description.text.toString()
                user.name = username.text.toString()
                helper.deleteUser()
                helper.insertUser(user)
                addInfoForUser(user)
                lastFragment = RegisterFragment()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        initFirebase()
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == RESULT_OK && data != null
        ) {
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                .child(helper.getUser().username)
            path.putFile(uri).addOnCompleteListener {
                if (it.isSuccessful) {
                    path.downloadUrl.addOnCompleteListener {
                        if (it.isSuccessful) {
                            val photoUrl = it.result.toString()
                            REF_DATABASE_ROOT.child(NODE_USERS)
                                .child(helper.getUser().username).child(PHOTO_URL)
                                .setValue(photoUrl)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        changeUserPhotoImage.downloadAndSetImage(photoUrl)
                                        makeToast(this, "data_update")
                                        user.photoUrl = photoUrl
                                    }
                                }
                        }
                    }
                }
            }
        }
    }
}