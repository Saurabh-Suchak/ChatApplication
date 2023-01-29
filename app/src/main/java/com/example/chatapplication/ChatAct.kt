package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ChatAct : AppCompatActivity() {

    private  lateinit var chatrecyclerv:RecyclerView
    private  lateinit var messagebox:EditText
    private lateinit var sendbtn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val intent=Intent()
        val name=intent.getStringExtra("name")
        val uid=intent.getStringExtra("uid")

        supportActionBar?.title =name

        chatrecyclerv=findViewById(R.id.chat_recycler)
        messagebox=findViewById(R.id.msg_box)
        sendbtn=findViewById(R.id.send_pic)
    }
}