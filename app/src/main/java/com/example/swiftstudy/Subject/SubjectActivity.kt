package com.example.swiftstudy.Subject

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swiftstudy.Database.DatabaseHelper
import com.example.swiftstudy.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SubjectActivity : AppCompatActivity() {

    private lateinit var subjectRecyclerView: RecyclerView
    private lateinit var subjectAdapter: SubjectAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)

        databaseHelper = DatabaseHelper(this)
        subjectRecyclerView = findViewById(R.id.subjectRecyclerView)
        val addSubjectButton: FloatingActionButton = findViewById(R.id.addSubjectButton)

        // Initialize RecyclerView
        subjectRecyclerView.layoutManager = LinearLayoutManager(this)
        subjectAdapter = SubjectAdapter(databaseHelper.getAllSubjects()) // Fetch from DB
        subjectRecyclerView.adapter = subjectAdapter

        // Add Subject Button
        addSubjectButton.setOnClickListener {
            showAddSubjectDialog()
        }
    }

    private fun showAddSubjectDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("New Subject")

        val input = EditText(this)
        input.hint = "Enter Subject Name"
        builder.setView(input)

        builder.setPositiveButton("Save") { _: DialogInterface, _: Int ->
            val subjectName = input.text.toString().trim()
            if (subjectName.isNotEmpty()) {
                val isInserted = databaseHelper.addSubject(subjectName)
                if (isInserted) {
                    Toast.makeText(this, "Subject added", Toast.LENGTH_SHORT).show()
                    refreshSubjects()
                } else {
                    Toast.makeText(this, "Failed to add subject", Toast.LENGTH_SHORT).show()
                }
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    private fun refreshSubjects() {
        subjectAdapter.updateData(databaseHelper.getAllSubjects())
    }
}
