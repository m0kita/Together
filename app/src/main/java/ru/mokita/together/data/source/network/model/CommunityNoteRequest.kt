package ru.mokita.together.data.source.network.model

data class CommunityNoteRequest(
    val title: String = "",
    val content: List<DataRichText>
)
