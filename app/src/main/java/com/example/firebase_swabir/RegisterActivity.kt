package com.example.firebase_swabir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var fullname:EditText
    private lateinit var mailreg:EditText
    private lateinit var passreg:EditText
    private lateinit var createbtn:Button
    //Initialize Firebase
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        fullname = findViewById(R.id.edtname)
        mailreg = findViewById(R.id.edtmail)
        passreg = findViewById(R.id.edtpass)
        createbtn = findViewById(R.id.btncreate)

        //Initialize Firebase again
        auth = FirebaseAuth.getInstance()


        createbtn.setOnClickListener {

            var name = fullname.text.toString().trim()
            var mail = mailreg.text.toString().trim()
            var pass = passreg.text.toString().trim()

            // Validate User Info
            if (name.isEmpty() || mail.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()

            } else {

                auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(this) {

                    if (it.isSuccessful) {
                        Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show()
                        // Navigate back to login
                        var gotologin =Intent(this, LoginActivity::class.java)
                        startActivity(gotologin)

                    } else {

                        Toast.makeText(this, "Failed to Create User", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }
}