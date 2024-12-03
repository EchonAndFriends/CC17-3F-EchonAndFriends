package com.example.swiftstudy.Subject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.R

class CreateSubjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_subject)

        val subjectNameInput: EditText = findViewById(R.id.newSubName)
        val saveButton: Button = findViewById(R.id.saveSubjectButton)

        saveButton.setOnClickListener {
            val subjectName = subjectNameInput.text.toString().trim()
            if (subjectName.isNotEmpty()) {
                // Save subject to database
                finish()
            }
        }
    }
}
