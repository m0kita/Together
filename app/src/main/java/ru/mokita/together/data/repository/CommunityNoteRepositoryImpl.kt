package ru.mokita.together.data.repository

import ru.mokita.together.data.source.network.api.TogetherApi
import ru.mokita.together.data.source.network.model.CommunityNoteRequest
import ru.mokita.together.data.toData
import ru.mokita.together.data.toUi
import ru.mokita.together.domain.model.CommunityNote
import ru.mokita.together.domain.model.RichText
import ru.mokita.together.domain.repository.CommunityNoteRepository
import javax.inject.Inject

class CommunityNoteRepositoryImpl @Inject constructor(
    private val networkSource: TogetherApi
): CommunityNoteRepository {
    override suspend fun getCommunityNotes(): List<CommunityNote> {
        val response = networkSource.getCommunityNotes()
        return response.data?.toUi() ?: emptyList()
    }

    override suspend fun getCommunityNote(): CommunityNote {
        val response = networkSource.getCommunityNotes()
        return response.data?.first()?.toUi() ?: CommunityNote()
    }

    override suspend fun createCommunityNote(title: String, content: List<RichText>) {
        val request = CommunityNoteRequest(title = title, content = content.toData())
        networkSource.createCommunityNote(communityNoteRequest = request)
    }
}