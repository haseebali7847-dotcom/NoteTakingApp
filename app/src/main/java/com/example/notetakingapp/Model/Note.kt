package com.example.notetakingapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,            // var so Room can set the generated id
    val title: String,
    val description: String
)
