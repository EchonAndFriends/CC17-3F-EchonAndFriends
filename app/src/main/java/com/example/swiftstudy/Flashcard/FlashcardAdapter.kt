package com.example.swiftstudy.flashcard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swiftstudy.R

class FlashcardAdapter(private var flashcards: List<Pair<String, String>>) :
    RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder>() {

    class FlashcardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val question: TextView = view.findViewById(R.id.flashcardQuestion)
        val answer: TextView = view.findViewById(R.id.flashcardAnswer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.flashcard_item, parent, false)
        return FlashcardViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlashcardViewHolder, position: Int) {
        val flashcard = flashcards[position]
        holder.question.text = flashcard.first
        holder.answer.text = flashcard.second
    }

    override fun getItemCount(): Int = flashcards.size

    fun updateData(newFlashcards: List<Pair<String, String>>) {
        flashcards = newFlashcards
        notifyDataSetChanged()
    }
}
