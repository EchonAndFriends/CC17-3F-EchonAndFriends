package com.example.swiftstudy.Subject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.R
import com.example.swiftstudy.Database.DatabaseHelper

class CreateSubjectActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_subject)

        databaseHelper = DatabaseHelper(this)
        val subjectNameInput: EditText = findViewById(R.id.newSubName)
        val saveButton: Button = findViewById(R.id.saveSubjectButton)

        saveButton.setOnClickListener {
            val subjectName = subjectNameInput.text.toString().trim()
            if (subjectName.isNotEmpty()) {
                // Assuming the user ID is passed, otherwise hardcode or get it dynamically
                val userId = 1 // Replace with the actual user ID
                if (databaseHelper.addSubject(userId, subjectName)) {
                    Toast.makeText(this, "Subject saved!", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity and go back
                } else {
                    Toast.makeText(this, "Error saving subject", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a subject name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
