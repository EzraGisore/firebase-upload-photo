package com.example.firebase_swabir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var enteremail:EditText
    private lateinit var enterpassword:EditText
    private lateinit var submitlogin:Button
    private lateinit var registerbutton:Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        enteremail = findViewById(R.id.edtemail)
        enterpassword = findViewById(R.id.edtpassword)
        submitlogin = findViewById(R.id.btnlogin)
        registerbutton = findViewById(R.id.btnregister)

        auth = FirebaseAuth.getInstance()

        registerbutton.setOnClickListener {
            // navigate user to the registration page
            var gotoreg = Intent(this, RegisterActivity::class.java)
            startActivity(gotoreg)

        }

        submitlogin.setOnClickListener {
            //

            var email = enteremail.text.toString().trim()
            var passcode = enterpassword.text.toString().trim()

            //Validate the Input
            if (email.isEmpty() || passcode.isEmpty()) {
                Toast.makeText(this, "Cannot Submit an Empty Field ", Toast.LENGTH_SHORT).show()
            } else{
                auth.signInWithEmailAndPassword(email, passcode).addOnCompleteListener(this) {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                        // Navigate a User to a different page
                        var gotomain = Intent(this, MainActivity::class.java)
                        startActivity(gotomain)
                        finish()

                    } else {
                        Toast.makeText(this, "Failed to login", Toast.LENGTH_SHORT).show()
                    }
                }

            }




        }

    }
}