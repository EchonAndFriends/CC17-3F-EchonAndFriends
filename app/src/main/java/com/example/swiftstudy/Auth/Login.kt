package com.example.swiftstudy.Auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.Main.Home
import com.example.swiftstudy.Database.DatabaseHelper
import com.example.swiftstudy.R
class Login : AppCompatActivity() {
    private lateinit var loginEmail: EditText
    private lateinit var loginPass: EditText
    private lateinit var finLoginButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        loginEmail = findViewById(R.id.loginEmail)
        loginPass = findViewById(R.id.loginPass)
        finLoginButton = findViewById(R.id.finLoginButton)

        dbHelper = DatabaseHelper(this)

        finLoginButton.setOnClickListener {
            val email = loginEmail.text.toString().trim()
            val password = loginPass.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                val isUserExists = dbHelper.checkUser(email, password)
                if (isUserExists) {
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, Home::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
