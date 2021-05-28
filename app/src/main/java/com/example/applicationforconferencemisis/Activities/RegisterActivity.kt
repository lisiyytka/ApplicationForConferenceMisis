package com.example.applicationforconferencemisis.Activities

import android.content.Intent
import android.os.Bundle
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

class RegisterActivity : AppCompatActivity() {
    private val registerFragment = RegisterFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.add(R.id.container_for_register, registerFragment)
        fragmentManager.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val helper = SQLiteHelper(this)
        val changeUserPhotoImage = findViewById<ImageView>(R.id.change_user_photo)
        val user = helper.getUser()
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