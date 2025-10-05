package com.example.notetakingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakingapp.Model.Note
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.NotesItemBinding

class NoteAdapter(
    private var notes: MutableList<Note>,
    private val onNoteClick: (Note) -> Unit,
    private val onDeleteClick: (Note) -> Unit,
    private val onUpdateClick: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: NotesItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]

        holder.binding.noteTitle.text = note.title
        holder.binding.noteDescription.text = note.description

        holder.binding.root.setOnClickListener {
            onNoteClick(note)
        }

        // ✅ Long click shows popup menu (Update / Delete)
        holder.binding.root.setOnLongClickListener { view ->
            val popup = PopupMenu(view.context, view)
            popup.inflate(R.menu.note_options_menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_update -> {
                        onUpdateClick(note)
                        true
                    }

                    R.id.menu_delete -> {
                        onDeleteClick(note)
                        true
                    }

                    else -> false
                }
            }
            popup.show()
            true
        }
    }

    override fun getItemCount() = notes.size

    fun updateList(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }
}
