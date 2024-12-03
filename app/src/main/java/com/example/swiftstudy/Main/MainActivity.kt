package com.example.swiftstudy.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.R
import com.example.swiftstudy.auth.Start

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, Start::class.java))
        finish() // Prevent going back to MainActivity
    }
}
