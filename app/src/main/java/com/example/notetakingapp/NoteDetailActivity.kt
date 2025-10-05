package com.example.notetakingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notetakingapp.databinding.NoteDetailBinding

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var binding: NoteDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = NoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val noteTitle = intent.getStringExtra("note_title") ?: ""
        val noteDescription = intent.getStringExtra("note_description") ?: ""


        binding.tvNoteTitle.text = noteTitle
        binding.tvNoteDescription.text = noteDescription


    }
}
