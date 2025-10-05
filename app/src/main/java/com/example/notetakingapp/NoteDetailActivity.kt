package com.example.notetakingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notetakingapp.databinding.NoteDetailBinding

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var binding: NoteDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding - make sure your layout filename matches binding class:
        // For NoteDetailBinding your layout should be: activity_note_detail.xml or note_detail.xml (matching the generated binding).
        binding = NoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data from Intent safely
        val noteTitle = intent.getStringExtra("note_title") ?: ""
        val noteDescription = intent.getStringExtra("note_description") ?: ""

        // Set text safely
        binding.tvNoteTitle.text = noteTitle
        binding.tvNoteDescription.text = noteDescription

        // If you want to keep them non-editable but allow scrolling, you already used TextView in layout.
    }
}
