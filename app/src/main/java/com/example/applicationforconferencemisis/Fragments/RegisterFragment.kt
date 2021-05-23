package com.example.applicationforconferencemisis.Fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Activities.RegisterActivity
import com.example.applicationforconferencemisis.Data.Firebase.FOLDER_PROFILE_IMAGE
import com.example.applicationforconferencemisis.Data.Firebase.REF_STORAGE_ROOT
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.makeToast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class RegisterFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onResume() {
        super.onResume()
        val changeUserPhoto = view!!.findViewById<ImageView>(R.id.change_user_photo)
        changeUserPhoto.setOnClickListener {
            changePhoto()
        }
    }

    private fun changePhoto() {
        CropImage.activity()
            .setAspectRatio(1,1)
            .setRequestedSize(600,600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(activity as RegisterActivity)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val helper = SQLiteHelper(context!!)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == RESULT_OK && data != null){
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                .child(helper.getUser().username)
            path.putFile(uri).addOnCompleteListener{
                makeToast(context!!,"data_update")
            }
        }
    }
}