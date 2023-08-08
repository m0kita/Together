package ru.mokita.together.domain.repository

import ru.mokita.together.domain.model.Note


interface NoteRepository {

    suspend fun getAllNotes(): List<Note>

    suspend fun getNote(): Note

    suspend fun insert(note: Note)

    suspend fun update(note: Note)

    suspend fun delete(note: Note)
}