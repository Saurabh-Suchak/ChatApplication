package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginAct : AppCompatActivity() {

    private lateinit var edtemail:EditText
    private lateinit var edtpass:EditText
    private lateinit var btnlogin:Button
    private lateinit var btnsignup:Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth= FirebaseAuth.getInstance()

        edtemail=findViewById(R.id.edit_email)
        edtpass= findViewById(R.id.edit_pass)
        btnlogin=findViewById(R.id.login_btn)
        btnsignup=findViewById(R.id.signup)

        btnsignup.setOnClickListener{
            val intent = Intent(this, SignUpAct::class.java)

            startActivity(intent)
        }

        btnlogin.setOnClickListener{
            val email=edtemail.text.toString()
            val pass = edtpass.text.toString()

            login(email,pass)
        }
    }

    private fun login(email:String, pass:String){
        mAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
                    val intent=Intent(this@LoginAct,MainActivity::class.java)
//                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithEmail:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
                    Toast.makeText(this@LoginAct,"user does not exist",Toast.LENGTH_SHORT).show()
                }
            }

    }
}