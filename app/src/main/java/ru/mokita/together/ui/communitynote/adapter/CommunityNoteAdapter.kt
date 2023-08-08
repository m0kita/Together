package ru.mokita.together.ui.communitynote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mokita.profnotes.databinding.ItemCommunityNoteBinding
import ru.mokita.together.domain.model.CommunityNote
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CommunityNoteAdapter(private val notes: List<CommunityNote>): RecyclerView.Adapter<CommunityNoteAdapter.CommunityNoteViewHolder>() {

    inner class CommunityNoteViewHolder(private val binding: ItemCommunityNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: CommunityNote) {
            val date = Date()
            date.time = note.date.toLong()
            val dateFormat = SimpleDateFormat("d MMMM", Locale("ru", "RU"))
            binding.tvCommunityNoteDate.text = dateFormat.format(date)
            binding.tvCommunityNoteTitle.text = note.title
            binding.tvUserName.text = note.author?.name
            binding.tvCommunityNoteDescription.text = note.content.first().text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityNoteViewHolder {
        val binding = ItemCommunityNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityNoteViewHolder(binding)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: CommunityNoteViewHolder, position: Int) {
        holder.onBind(notes[position])
    }
}