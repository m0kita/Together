package ru.mokita.together.data.repository

import ru.mokita.together.data.source.database.NoteDatabase
import ru.mokita.together.data.source.database.dao.NoteDao
import ru.mokita.together.data.toData
import ru.mokita.together.data.toUi
import ru.mokita.together.domain.model.Note
import ru.mokita.together.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val databaseSource: NoteDao
): NoteRepository {
    override suspend fun getAllNotes(): List<Note> = databaseSource.getAllNotes().toUi()

    override suspend fun getNote(): Note {
        val note = databaseSource.getNote()
        if(note != null) {
            return note.toUi()
        } else {
            return Note()
        }
    }

    override suspend fun insert(note: Note) = databaseSource.insert(note.toData())

    override suspend fun update(note: Note) = databaseSource.update(note.toData())

    override suspend fun delete(note: Note) = databaseSource.delete(note.toData())
}