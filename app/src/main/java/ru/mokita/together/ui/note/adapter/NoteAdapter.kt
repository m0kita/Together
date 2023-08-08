package ru.mokita.together.ui.note.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mokita.profnotes.databinding.ItemNoteBinding
import ru.mokita.together.domain.model.Note

class NoteAdapter(private val notes: List<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: Note) {
            binding.tvNoteTitle.text = note.title
            binding.tvNoteDescription.text = note.description
            binding.tvNoteDate.text = note.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(notes[position])
    }
}
