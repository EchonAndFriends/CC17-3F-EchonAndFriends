package com.example.swiftstudy.Subject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.R
import com.example.swiftstudy.Flashcard.FlashcardMenu
import com.example.swiftstudy.Notes.NotesActivity

class in_sub : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_sub)

        // Connect the flashcards button
        val flashcardsButton: ImageView = findViewById(R.id.flashcardsImage)
        flashcardsButton.setOnClickListener {
            startActivity(Intent(this, FlashcardMenu::class.java))
        }

        // Connect the notes button
        val notesButton: ImageView = findViewById(R.id.notesButton)
        notesButton.setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }
    }
}
