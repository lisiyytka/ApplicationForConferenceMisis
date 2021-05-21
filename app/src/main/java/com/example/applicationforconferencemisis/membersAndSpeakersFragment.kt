package com.example.applicationforconferencemisis

import android.os.Bundle
import android.os.MemoryFile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationforconferencemisis.Data.Firebase.AppValueEventListener
import com.example.applicationforconferencemisis.Data.Firebase.NODE_USERS
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Models.User
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference

class membersAndSpeakersFragment : Fragment() {
    lateinit var membersRecyclerView: RecyclerView
    lateinit var mRefMembers: DatabaseReference
    private lateinit var mAdapter: FirebaseRecyclerAdapter<User, MembersHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_members_speakers, container, false)
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        membersRecyclerView = view!!.findViewById(R.id.members_recycler_view)
        mRefMembers = REF_DATABASE_ROOT.child(NODE_USERS)

        val option = FirebaseRecyclerOptions.Builder<User>()
            .setQuery(mRefMembers, User::class.java)
            .build()

        mAdapter = object : FirebaseRecyclerAdapter<User, MembersHolder>(option){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.members_speakers_component, parent, false)
                return MembersHolder(view)
            }

            override fun onBindViewHolder(holder: MembersHolder, position: Int, model: User) {

                mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS).child(model.id)

                mRefUsersListener = AppValueEventListener {
                    val contact = it.getCommonModel()
                    holder.name.text = contact.fullname
                    holder.status.text = contact.state
                    holder.photo.downloadAndSetImage(contact.photoUrl)
                    holder.itemView.setOnClickListener { replaceFragment(SingleChatFragment(contact)) }
                }

                mRefUsers.addValueEventListener(mRefUsersListener)
                mapListeners[mRefUsers] = mRefUsersListener
            }

            class MembersHolder(view: View): RecyclerView.ViewHolder(view){

            }

        }

    }
}