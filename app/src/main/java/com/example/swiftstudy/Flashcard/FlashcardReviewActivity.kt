package com.example.swiftstudy.Flashcard

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.R

class FlashcardReviewActivity : AppCompatActivity() {

    private var currentCardIndex = 0
    private val flashcards = listOf(
        Pair("What is Kotlin?", "A modern programming language."),
        Pair("What is Android?", "An operating system for mobile devices.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard_review)

        val questionTextView: TextView = findViewById(R.id.flashcardQuestion)
        val answerTextView: TextView = findViewById(R.id.flashcardAnswer)
        val nextButton: TextView = findViewById(R.id.nextCardButton)

        displayCard()

        questionTextView.setOnClickListener {
            answerTextView.visibility = TextView.VISIBLE
        }

        answerTextView.setOnClickListener {
            answerTextView.visibility = TextView.GONE
        }

        nextButton.setOnClickListener {
            currentCardIndex = (currentCardIndex + 1) % flashcards.size
            displayCard()
        }
    }

    private fun displayCard() {
        val questionTextView: TextView = findViewById(R.id.flashcardQuestion)
        val answerTextView: TextView = findViewById(R.id.flashcardAnswer)

        questionTextView.text = flashcards[currentCardIndex].first
        answerTextView.text = flashcards[currentCardIndex].second
        answerTextView.visibility = TextView.GONE
    }
}
