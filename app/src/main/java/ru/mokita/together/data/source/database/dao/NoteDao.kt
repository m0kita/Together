package ru.mokita.together.data.source.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.mokita.together.data.source.database.model.DatabaseNote

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<DatabaseNote>

    @Query("SELECT * FROM notes ORDER BY id DESC LIMIT 1")
    suspend fun getNote(): DatabaseNote

    @Insert
    suspend fun insert(note: DatabaseNote)

    @Update
    suspend fun update(note: DatabaseNote)

    @Delete
    suspend fun delete(note: DatabaseNote)
}