package com.example.swiftstudy.Flashcard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.R

class FlashcardMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard_menu)

        val startReviewButton: Button = findViewById(R.id.startReviewButton)
        val makeFlashcardsButton: Button = findViewById(R.id.makeFlashcardsButton)

        startReviewButton.setOnClickListener {
            startActivity(Intent(this, FlashcardReviewActivity::class.java))
        }

        makeFlashcardsButton.setOnClickListener {
            startActivity(Intent(this, NewFlashcardActivity::class.java))
        }
    }
}
