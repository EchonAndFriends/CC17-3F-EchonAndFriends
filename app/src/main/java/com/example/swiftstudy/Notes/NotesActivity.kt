package com.example.swiftstudy.Notes

import android.os.Bundle
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.R

class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val noteTitle: EditText = findViewById(R.id.noteTitle)
        val noteContent: EditText = findViewById(R.id.noteContent)
        val saveNoteButton: FloatingActionButton = findViewById(R.id.saveNoteButton)

        saveNoteButton.setOnClickListener {
            val title = noteTitle.text.toString().trim()
            val content = noteContent.text.toString().trim()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                // Save note logic
                finish()
            }
        }
    }
}
