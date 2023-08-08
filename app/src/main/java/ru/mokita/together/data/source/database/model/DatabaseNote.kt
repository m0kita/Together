package ru.mokita.together.data.source.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class DatabaseNote(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val date: String
)
