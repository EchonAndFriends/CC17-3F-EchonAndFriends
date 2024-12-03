package com.example.swiftstudy.Subject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swiftstudy.R

class SubjectAdapter(
    private var subjects: List<Pair<Int, String>>,
    private val onDelete: (Int) -> Unit,
    private val onEdit: (Int, String) -> Unit,
    private val onClick: (Int) -> Unit // Add this line for click handling
) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_subject_view, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val (subjectId, subjectName) = subjects[position]
        holder.subjectNameTextView.text = subjectName

        // Set up onClick listener for the subject item
        holder.itemView.setOnClickListener {
            onClick(subjectId) // Call the onClick function passed to the adapter
        }

        holder.deleteIcon.setOnClickListener {
            onDelete(subjectId) // Delete the subject
        }

        holder.editIcon.setOnClickListener {
            onEdit(subjectId, subjectName) // Edit the subject
        }
    }

    override fun getItemCount(): Int = subjects.size

    fun updateData(newSubjects: List<Pair<Int, String>>) {
        subjects = newSubjects
        notifyDataSetChanged()
    }

    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectNameTextView: TextView = itemView.findViewById(R.id.subjectName)
        val deleteIcon: ImageView = itemView.findViewById(R.id.deleteIcon)
        val editIcon: ImageView = itemView.findViewById(R.id.editIcon)
    }
}
