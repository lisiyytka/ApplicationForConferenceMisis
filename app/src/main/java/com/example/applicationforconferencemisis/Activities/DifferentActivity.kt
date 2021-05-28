package com.example.applicationforconferencemisis.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Data.Firebase.*
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.Fragments.*
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.downloadAndSetImage
import com.example.applicationforconferencemisis.makeToast
import com.example.applicationforconferencemisis.replaceFragment
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.coroutines.delay


var lastFragment: Fragment? = null
var lastBtnId: Int = 0
var fragmentName: TextView? = null
var lastFragmentString: String = ""
var lastDateBtn = "june 3"

class DifferentActivity : AppCompatActivity() {

    private val helper = SQLiteHelper(this)
    private val mainScheduleFragment = MainScheduleFragment()

    //    lateinit var scheduleFragment: ScheduleAndMyScheduleFragment
    private val groupChatFragment = GroupChatFragment()
    private val messagesFragment = MessagesFragment()
    private val membersFragment = MembersFragment()
    private val accountFragment = AccountFragment()
    private val myScheduleFragment = MyScheduleFragment()
    private val membersAndSpeakers = MembersAndSpeakersFragment()
//    private val conferenceFragment = ConferenceFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)
        makeToast(this, helper.getUser().username)
        val backBtn = findViewById<ImageView>(R.id.back)
        backBtn.setOnClickListener {
            if (lastFragment == null) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(
                    Intent(this, DifferentActivity::class.java).putExtra(
                        "buttonId",
                        lastBtnId
                    )
                )
            }
        }
        fragmentName = findViewById(R.id.fragment_name)

        val buttonId = intent.getIntExtra("buttonId", 0)
        val fragmentManager = supportFragmentManager.beginTransaction()
        when (buttonId) {
            R.id.schedule_btn -> {
                lastFragment = null
                fragmentName!!.text = "Schedule"
                lastFragmentString = "MainSchedule"
                fragmentManager.add(R.id.containerForFrag, mainScheduleFragment)
            }
            R.id.my_schedule_btn -> {
                lastFragment = null
                fragmentName!!.text = "My schedule"
                lastFragmentString = "MySchedule"
                fragmentManager.add(R.id.containerForFrag, myScheduleFragment)
            }
            R.id.group_chat_btn -> {
                lastFragment = null
                fragmentName!!.text = "Group chat"
                fragmentManager.add(R.id.containerForFrag, groupChatFragment)
            }
            R.id.messages_btn -> {
                lastFragment = null
                fragmentName!!.text = "Messages"
                fragmentManager.add(R.id.containerForFrag, messagesFragment)
            }
            R.id.members_btn -> {
                lastFragment = null
                fragmentName!!.text = "Members"
                fragmentManager.add(R.id.containerForFrag, membersFragment)
            }
            R.id.speakers_btn -> {
                lastFragment = null
                fragmentName!!.text = "Speakers"
                fragmentManager.add(R.id.containerForFrag, membersAndSpeakers)
            }
            R.id.account_btn -> {
                lastFragment = null
                fragmentName!!.text = "Account"
                fragmentManager.add(R.id.containerForFrag, accountFragment)
            }
        }
        fragmentManager.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val helper = SQLiteHelper(this)
        val changeUserPhotoImage = findViewById<ImageView>(R.id.change_user_photo)
        val nextBtn = findViewById<ImageButton>(R.id.next_btn)
        val username = findViewById<EditText>(R.id.user_name_surname)
        val description = findViewById<EditText>(R.id.inf_about_user)
        val fragmentManager = supportFragmentManager.beginTransaction()
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