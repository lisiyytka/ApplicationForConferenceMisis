package com.example.applicationforconferencemisis.Activities

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationforconferencemisis.Data.Firebase.FOLDER_PROFILE_IMAGE
import com.example.applicationforconferencemisis.Data.Firebase.REF_STORAGE_ROOT
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.makeToast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        makeToast(this,"asdasdasd")
        val changePhoto = findViewById<ImageView>(R.id.change_user_photo)
        changePhoto.setOnClickListener {
            changeUserPhoto()
        }
    }

    private fun changeUserPhoto() {
        CropImage.activity()
            .setAspectRatio(1,1)
            .setRequestedSize(600,600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val helper = SQLiteHelper(this)
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode== RESULT_OK && data !=null){
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                .child(helper.getUser().username)
            path.putFile(uri).addOnCompleteListener{
                if (it.isSuccessful){
                    makeToast(this, "data_update")
                }
            }
        }
    }
}