package com.example.firebase_swabir

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var enterbrand:EditText
    private lateinit var entermodel:EditText
    private lateinit var enterprice:EditText
    private lateinit var uploadphoto:Button
    private lateinit var uploaddata:Button
    private lateinit var viewbutton:Button


    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enterbrand = findViewById(R.id.edtBrand)
        entermodel = findViewById(R.id.edtModel)
        enterprice = findViewById(R.id.edtPrice)
        uploadphoto = findViewById(R.id.btnImage)
        uploaddata = findViewById(R.id.btnUpload)
        viewbutton = findViewById(R.id.btnView)

        var database = FirebaseDatabase.getInstance()
        var databaseRef = database.getReference("Cars")



        // Uploading car photo
        uploadphoto.setOnClickListener {


        }


        // Uploading all Data
        uploaddata.setOnClickListener {

        var carbrand = enterbrand.text.toString().trim()
        var carmodel = entermodel.text.toString().trim()
        var carprice = enterprice.text.toString().trim()

            var id = System.currentTimeMillis().toString()

            var ref = database.getReference("car"+id)

            // Validation
            if (carbrand.isEmpty() || carmodel.isEmpty() || carprice.isEmpty()) {

                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()
            } else {

                //Proceed to Save Data
                var usercar = Car(carbrand,carmodel,carprice,id)

                // Try to upload to firebase
                var ref = FirebaseDatabase.getInstance().getReference().child("Car")

                ref.setValue(usercar).addOnCompleteListener{

                    if (it.isSuccessful) {
                        Toast.makeText(this, "Car Data Uploaded", Toast.LENGTH_SHORT).show()
                    } else {

                        Toast.makeText(this, "Error! Could not Upload Data", Toast.LENGTH_SHORT).show()
                    }
                }


            }
        }


        //Viewing Uploaded Data
        viewbutton.setOnClickListener {

            var viewcars = Intent(this, ViewCars::class.java)
            startActivity(viewcars)


        }
    }
}