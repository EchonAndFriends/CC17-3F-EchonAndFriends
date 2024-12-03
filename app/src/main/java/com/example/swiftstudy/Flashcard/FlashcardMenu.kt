package com.example.swiftstudy.Flashcard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FlashcardMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard_menu)

        val startReviewButton: FloatingActionButton = findViewById(R.id.startReviewButton)
        val makeFlashcardsButton: FloatingActionButton = findViewById(R.id.makeFlashcardsButton)

        startReviewButton.setOnClickListener {
            startActivity(Intent(this, FlashcardReviewActivity::class.java))
        }

        makeFlashcardsButton.setOnClickListener {
            startActivity(Intent(this, NewFlashcardActivity::class.java))
        }
    }
}
