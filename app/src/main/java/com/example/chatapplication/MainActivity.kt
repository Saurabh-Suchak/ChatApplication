package com.example.chatapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private  lateinit var userrecycler:RecyclerView
    private  lateinit var userList:ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var mauth:FirebaseAuth
    private lateinit var mdbref:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mauth= FirebaseAuth.getInstance()
        mdbref=FirebaseDatabase.getInstance().getReference()

        userList= ArrayList()
        adapter= UserAdapter(this,userList)
        userrecycler=findViewById(R.id.user_recycler_view)
        userrecycler.layoutManager= LinearLayoutManager(this)
        userrecycler.adapter=adapter

        var addValueEventListener =
            mdbref.child("user").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    userList.clear()
                    for (postSnapshot in snapshot.children) {
                        val currentuser = postSnapshot.getValue(User::class.java)
                        if (mauth.currentUser?.uid != currentuser?.uid) {
                            userList.add(currentuser!!)
                        }
                    }
                adapter.notifyDataSetChanged()
                }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
            }

                    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.menu, menu)

                return super.onCreateOptionsMenu(menu)
            }

                    override fun onOptionsItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.logout) {
                    mauth.signOut()
                    finish()
                    return true
                }
                return true

            }
    }