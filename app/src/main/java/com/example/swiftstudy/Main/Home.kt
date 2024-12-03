package com.example.swiftstudy.Main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.R
import com.example.swiftstudy.Home.ResourceRecommender
import com.example.swiftstudy.Subject.SubjectActivity
import com.example.swiftstudy.Home.Translate

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val resourceRecommenderButton = findViewById<Button>(R.id.ResourceRecommenderButton)
        val translateButton = findViewById<Button>(R.id.TranslateButton)
        val subjectButton = findViewById<Button>(R.id.SubjectButton)

        resourceRecommenderButton.setOnClickListener {
            val intent = Intent(this, ResourceRecommender::class.java)
            startActivity(intent)
        }

        translateButton.setOnClickListener {
            val intent = Intent(this, Translate::class.java)
            startActivity(intent)
        }

        subjectButton.setOnClickListener {
            val intent = Intent(this, SubjectActivity::class.java)
            startActivity(intent)
        }
    }
}
