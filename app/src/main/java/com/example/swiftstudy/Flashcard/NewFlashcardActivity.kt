package com.example.swiftstudy.Flashcard

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.R

class NewFlashcardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_flashcards)

        val questionInput: EditText = findViewById(R.id.flashcardQuestionInput)
        val answerInput: EditText = findViewById(R.id.flashcardAnswerInput)
        val saveButton: Button = findViewById(R.id.saveFlashcardButton)

        saveButton.setOnClickListener {
            val question = questionInput.text.toString().trim()
            val answer = answerInput.text.toString().trim()

            if (question.isNotEmpty() && answer.isNotEmpty()) {
                // Save the flashcard (use database or local storage)
                Toast.makeText(this, "Flashcard saved!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Both fields are required.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
