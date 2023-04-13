package com.example.TakeNote.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.TakeNote.Model.Notes
import com.example.TakeNote.R
import com.example.TakeNote.databinding.ItemNotesBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesAdapterViewHolder>() {
    var notesList = listOf<Notes>()
    lateinit var onNoteClick: (Notes) -> Unit
    fun setMyNotesList(list: List<Notes>) {
        notesList = list
        notifyDataSetChanged()
    }

    class NotesAdapterViewHolder(val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapterViewHolder {
        return NotesAdapterViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesAdapterViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.title.text = data.title
        holder.binding.notes.text = data.notes
        holder.binding.date.text = data.date

        when (data.priority) {
            "1" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.blue_dot)

            }

            "2" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.green_dot)


            }
            "3" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.red_dot)

            }
            "4" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.yellow_dot)

            }

        }


        holder.binding.root.setOnClickListener {

            onNoteClick.invoke(notesList[position])


        }


    }


}
