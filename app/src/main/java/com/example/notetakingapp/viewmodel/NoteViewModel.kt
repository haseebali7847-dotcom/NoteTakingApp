package com.example.notetakingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.data.NoteDatabase
import com.example.notetakingapp.Model.Note
import com.example.notetakingapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    // Repository handles data operations
    private val repository: NoteRepository

    // LiveData to observe all notes
    val allNotes: LiveData<List<Note>>

    init {
        // Get DAO from Room Database
        val noteDao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(noteDao)

        // Initialize LiveData
        allNotes = repository.allNotes
    }

    // Insert a new note
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    // Delete a note
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
}