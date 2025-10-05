package com.example.notetakingapp.repository
import androidx.lifecycle.LiveData
import com.example.notetakingapp.data.NoteDao
import com.example.notetakingapp.Model.Note


class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insertNote(note)
    }

    // Delete a note from database
    suspend fun delete(note: Note) {
        noteDao.deleteNote(note)
    }
}


