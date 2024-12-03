package com.example.swiftstudy.Notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_menu)

        // Corrected ID and type
        val addNoteButton: FloatingActionButton = findViewById(R.id.addNoteButton)

        addNoteButton.setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }
    }
}
