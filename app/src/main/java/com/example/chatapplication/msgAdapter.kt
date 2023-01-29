package com.example.chatapplication

import android.content.Context
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

//private val Message.message: CharSequence?
//    get() {return  message}

private val Any.message: String?
    get() {return message}

class msgAdapter(val context:Context, val messageList:ArrayList<Message>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_RECEIVE=1
    val ITEM_SENT=2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==1){
            val view:View= LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return ReceiveViewHolder(view)

        }
        else{
            val view:View= LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return sentViewHolder(view)

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentmsg=messageList[position]
        if(holder.javaClass==sentViewHolder::class.java){

            val viewHolder=holder as sentViewHolder
            holder.sentmsg.text=currentmsg.message

        }
        else{
            val viewHolder=holder as ReceiveViewHolder
            holder.recmsg.text=currentmsg.message

        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentmsg=messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid?.equals(currentmsg.sendingUid) == true){
            return ITEM_SENT
        }
        else{
            return ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size

    }

    class sentViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val sentmsg= itemView.findViewById<TextView>(R.id.txt_sent)

    }

    class ReceiveViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val recmsg= itemView.findViewById<TextView>(R.id.txt_rec)

    }


}