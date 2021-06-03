package com.example.applicationforconferencemisis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.Image
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Data.Firebase.*
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.MainSchedule
import com.example.applicationforconferencemisis.Data.Models.User
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

fun makeToast(context: Context, arg: String) {
    Toast.makeText(context, arg, Toast.LENGTH_LONG).show()
}

fun Fragment.replaceFragment(fragment: Fragment) {
    /* Функция расширения для Fragment, позволяет устанавливать фрагменты */
    this.fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(
            R.id.containerForFrag,
            fragment
        )?.commit()
}

fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}

fun String.asDate(): String {
    val date = Date(this.toLong())
    val timeFormat = SimpleDateFormat("dd", Locale.getDefault())
    return timeFormat.format(date)
}

fun ImageView.downloadAndSetImage(url: String) {
    if (url == "") {
        val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
            .child("kisspng-refilmery-computer-icons-avatar-user-profile-avatar-vector-5ad7bb8f92b678_25771326152408769561009.png")
        path.downloadUrl.addOnCompleteListener {
            val photoUrl = it.result.toString()
            Picasso.get()
                .load(photoUrl)
                .placeholder(R.drawable.ic_profile)
                .into(this)
        }
    } else {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_profile)
            .into(this)
    }
}
