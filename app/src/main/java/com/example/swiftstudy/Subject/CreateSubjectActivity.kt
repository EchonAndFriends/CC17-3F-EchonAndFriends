package com.example.swiftstudy.Subject

import android.content.Intent
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
                // Add subject to the database
                val userId = 1 // Replace with actual user ID
                val success = databaseHelper.addSubject(userId, subjectName)

                if (success) {
                    Toast.makeText(this, "Subject added.", Toast.LENGTH_SHORT).show()

                    // Go back to the SubjectActivity and refresh the subjects
                    val intent = Intent(this, SubjectActivity::class.java)
                    startActivity(intent)
                    finish() // Finish the current activity
                } else {
                    Toast.makeText(this, "Failed to add subject.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a subject name.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
