package com.example.applicationforconferencemisis

import android.os.Bundle
import android.os.MemoryFile
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
    private lateinit var mRefUsersListener:AppValueEventListener
    private  var mapListeners = hashMapOf<DatabaseReference,AppValueEventListener>()

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
        membersRecyclerView.layoutManager = LinearLayoutManager(context)
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

                mRefUsersListener = AppValueEventListener {
//                    for(child in it.children){
//                        val member = child.getValue(User::class.java)
//                        listUser.add(member!!)
//                        if (member != null) {
//                            holder.userName.text =
//                        }
//                        holder.itemView.setOnClickListener {
//                            if (member != null) {
//                                replaceFragment(groupChatFragment(member.username))
//                            }
//                        }
//                    }
                    val list = it.getValue(List<String>())
                }
                mRefMembers.addListenerForSingleValueEvent(mRefUsersListener)
                mapListeners[mRefMembers] = mRefUsersListener
            }
        }
        membersRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    class MembersHolder (view: View): RecyclerView.ViewHolder(view){
        var userName: TextView = itemView.findViewById(R.id.users_name)
//        var status: TextView = itemView.findViewById(R.id.users_status)
    }
}