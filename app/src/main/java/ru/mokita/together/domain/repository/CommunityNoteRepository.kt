package ru.mokita.together.domain.repository

import ru.mokita.together.domain.model.CommunityNote
import ru.mokita.together.domain.model.RichText

interface CommunityNoteRepository {
    suspend fun getCommunityNotes(): List<CommunityNote>

    suspend fun getCommunityNote(): CommunityNote

    suspend fun createCommunityNote(title: String, content: List<RichText>)
}