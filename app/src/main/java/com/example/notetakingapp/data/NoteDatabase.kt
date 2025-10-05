package com.example.notetakingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notetakingapp.Model.Note



// 1️⃣ Step 1: Tell Room which tables (entities) exist and the version of the database
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            // If already exists, return it
            return INSTANCE ?: synchronized(this) {
                // If not exists, create a new instance
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database" // <-- database file name
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}