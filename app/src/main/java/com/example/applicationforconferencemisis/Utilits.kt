package com.example.applicationforconferencemisis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun makeToast(context: Context, arg: String) {
    Toast.makeText(context, arg, Toast.LENGTH_LONG).show()
}

fun Fragment.replaceFragment(fragment: Fragment){
    /* Функция расширения для Fragment, позволяет устанавливать фрагменты */
    this.fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(R.id.containerForFrag,
            fragment
        )?.commit()
}

fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}
