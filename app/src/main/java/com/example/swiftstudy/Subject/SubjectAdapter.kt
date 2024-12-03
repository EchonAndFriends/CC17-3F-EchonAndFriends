package com.example.swiftstudy.subject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.swiftstudy.R

class SubjectAdapter(
    private val subjects: MutableList<Pair<Int, String>>, // Pair of subject ID and name
    private val onDelete: (Int) -> Unit,
    private val onEdit: (Int, String) -> Unit
) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    inner class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subjectName: EditText = view.findViewById(R.id.scienceTextInput)
        val editButton: ImageView = view.findViewById(R.id.editIcon)
        val deleteButton: ImageView = view.findViewById(R.id.deleteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_subject_view, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val (subjectId, subjectName) = subjects[position]
        holder.subjectName.setText(subjectName)

        holder.editButton.setOnClickListener {
            holder.subjectName.isFocusableInTouchMode = true
            holder.subjectName.requestFocus()
            val updatedName = holder.subjectName.text.toString().trim()
            onEdit(subjectId, updatedName)
        }


        holder.deleteButton.setOnClickListener {
            onDelete(subjectId)
        }
    }

    override fun getItemCount(): Int = subjects.size

    fun updateData(newSubjects: List<Pair<Int, String>>) {
        subjects.clear()
        subjects.addAll(newSubjects)
        notifyDataSetChanged()
    }
}
