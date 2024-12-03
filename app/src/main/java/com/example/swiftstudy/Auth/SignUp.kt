package com.example.swiftstudy.Auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.Database.DatabaseHelper
import com.example.swiftstudy.R


class SignUp : AppCompatActivity() {
    private lateinit var suName: EditText
    private lateinit var suEmail: EditText
    private lateinit var suPass: EditText
    private lateinit var finRegisterButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        suName = findViewById(R.id.suName)
        suEmail = findViewById(R.id.suEmail)
        suPass = findViewById(R.id.suPass)
        finRegisterButton = findViewById(R.id.finRegisterButton)

        dbHelper = DatabaseHelper(this)

        finRegisterButton.setOnClickListener {
            val name = suName.text.toString().trim()
            val email = suEmail.text.toString().trim()
            val password = suPass.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                val isInserted = dbHelper.addUser(email, password)
                if (isInserted) {
                    Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, Login::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Registration failed. User may already exist.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
