package ru.mokita.together.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mokita.together.data.source.database.dao.NoteDao
import ru.mokita.together.data.source.database.model.DatabaseNote

@Database(entities = [DatabaseNote::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}