package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpAct : AppCompatActivity() {

    private lateinit var edtemail: EditText
    private lateinit var edtpass: EditText
    private lateinit var edtname: EditText
    private lateinit var btnsignup: Button
    private lateinit var mAuth:FirebaseAuth
    private lateinit var mdbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        mAuth= FirebaseAuth.getInstance()

        edtemail=findViewById(R.id.edit_email)
        edtpass= findViewById(R.id.edit_pass)
        edtname=findViewById(R.id.edit_name)
        btnsignup=findViewById(R.id.signup)

        btnsignup.setOnClickListener{
            val email=edtemail.text.toString()
            val pass = edtpass.text.toString()
            val name=edtname.text.toString()

            signup(name,email,pass)
        }


    }

    private fun signup(name:String,email:String, pass:String){
        mAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addusertodatabase(name,email,mAuth.currentUser?.uid!!)
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
                    val intent=Intent(this@SignUpAct,MainActivity::class.java)
//                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
                    Toast.makeText(this@SignUpAct,"Error occured",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addusertodatabase(name:String,email:String,uid:String){
        mdbref=FirebaseDatabase.getInstance().getReference()
        mdbref.child("user").child(uid).setValue(User(name,email,uid))

    }
}