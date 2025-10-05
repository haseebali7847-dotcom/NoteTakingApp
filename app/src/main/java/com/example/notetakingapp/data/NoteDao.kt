package com.example.notetakingapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.example.notetakingapp.Model.Note

@Dao
interface NoteDao {

    // Insert a new note into the database (don't replace on conflict)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    // Delete a specific note from the database
    @Delete
    suspend fun deleteNote(note: Note)

    // Get all notes sorted by newest first — table name matches entity exactly
    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>
}
