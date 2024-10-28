package com.example.swiftstudy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

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
            val intent = Intent(this, Subject::class.java)
            startActivity(intent)
        }
    }
}
