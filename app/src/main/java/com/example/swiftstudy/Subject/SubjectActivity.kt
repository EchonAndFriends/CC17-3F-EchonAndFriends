package com.example.swiftstudy.Subject

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swiftstudy.Database.DatabaseHelper
import com.example.swiftstudy.R
import com.example.swiftstudy.subject.SubjectAdapter

class SubjectActivity : AppCompatActivity() {

    private lateinit var subjectRecyclerView: RecyclerView
    private lateinit var subjectAdapter: SubjectAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)

        databaseHelper = DatabaseHelper(this)
        subjectRecyclerView = findViewById(R.id.subjectRecyclerView)
        val addSubjectButton: TextView = findViewById(R.id.addSubjectButton)

        val subjects = databaseHelper.getSubjects(userId = 1) // Replace with actual user ID
            .mapIndexed { index, name -> Pair(index + 1, name) }.toMutableList()

        subjectAdapter = SubjectAdapter(subjects,
            onDelete = { subjectId ->
                if (databaseHelper.deleteSubject(subjectId)) {
                    Toast.makeText(this, "Subject deleted.", Toast.LENGTH_SHORT).show()
                    refreshSubjects()
                }
            },
            onEdit = { subjectId, subjectName ->
                showEditSubjectDialog(subjectId, subjectName)
            })

        subjectRecyclerView.adapter = subjectAdapter
        subjectRecyclerView.layoutManager = LinearLayoutManager(this)

        addSubjectButton.setOnClickListener {
            startActivity(Intent(this, CreateSubjectActivity::class.java))
        }
    }

    private fun showEditSubjectDialog(subjectId: Int, oldName: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Edit Subject")

        val input = EditText(this)
        input.hint = "Enter new subject name"
        input.setText(oldName)

        dialog.setView(input)
        dialog.setPositiveButton("Save") { _, _ ->
            val newName = input.text.toString()
            if (databaseHelper.updateSubject(subjectId, newName)) {
                Toast.makeText(this, "Subject updated.", Toast.LENGTH_SHORT).show()
                refreshSubjects()
            }
        }

        dialog.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        dialog.show()
    }

    private fun refreshSubjects() {
        val subjects = databaseHelper.getSubjects(userId = 1)
            .mapIndexed { index, name -> Pair(index + 1, name) }
        subjectAdapter.updateData(subjects)
    }
}
