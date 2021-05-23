package com.example.applicationforconferencemisis.Activities

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log.i
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationforconferencemisis.Data.Firebase.FOLDER_PROFILE_IMAGE
import com.example.applicationforconferencemisis.Data.Firebase.REF_STORAGE_ROOT
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.Fragments.RegisterFragment
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.makeToast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class RegisterActivity : AppCompatActivity() {
    private val registerFragment = RegisterFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        makeToast(this,"asdasdasd")
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.add(R.id.container_for_register,registerFragment)
        fragmentManager.commit()
    }
}