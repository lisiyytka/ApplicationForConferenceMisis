package com.example.applicationforconferencemisis.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Activities.fragmentName
import com.example.applicationforconferencemisis.Activities.lastBtnId
import com.example.applicationforconferencemisis.Activities.lastFragment
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_USERS
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Firebase.addNewDialog
import com.example.applicationforconferencemisis.Data.Models.User
import com.example.applicationforconferencemisis.Data.SQLite.SQLiteHelper
import com.example.applicationforconferencemisis.R
import com.example.applicationforconferencemisis.downloadAndSetImage
import com.example.applicationforconferencemisis.makeToast
import com.example.applicationforconferencemisis.replaceFragment
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference

class MembersFragment : Fragment() {

    lateinit var membersRecyclerView: RecyclerView
    lateinit var mRefMembers: DatabaseReference
    private lateinit var adapter: RecyclerView.Adapter<MembersHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.members_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        REF_DATABASE_ROOT.child(NODE_USERS).addListenerForSingleValueEvent(
            AppValueEventListener {
                val mListUsers = it.children.map { it.getValue(User::class.java)!! }
                val a = arrayListOf<User>()
                for (i in mListUsers) {
                    if (i.role == "DefaultUser") {
                        a.add(i)
                    }
                }
                initRecyclerView(a)
            }
        )
    }

    private fun initRecyclerView(a: List<User>) {
        membersRecyclerView = view!!.findViewById(R.id.members_recycler_view)
        membersRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = object : RecyclerView.Adapter<MembersHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.members_default_component, parent, false)
                return MembersHolder(view)
            }

            override fun onBindViewHolder(holder: MembersHolder, position: Int) {
                holder.userName.text = a[position].name
                holder.imgProfile.downloadAndSetImage(a[position].photoUrl)
                holder.sendMessageButton.setOnClickListener {
                    lastFragment = MembersAndSpeakersFragment()
                    lastBtnId = R.id.members_btn
                    fragmentName!!.text = a[position].name
                    replaceFragment(SingleChatFragment(a[position].username))
                }
            }

            override fun getItemCount() = a.size

        }

        membersRecyclerView.adapter = adapter
    }

    class MembersHolder (view: View): RecyclerView.ViewHolder(view){
        var userName: TextView = itemView.findViewById(R.id.users_default_name)
        var sendMessageButton: ImageView = itemView.findViewById(R.id.send_default_msg_btn)
        var imgProfile: ImageView = itemView.findViewById(R.id.img_default_profile)
    }
}