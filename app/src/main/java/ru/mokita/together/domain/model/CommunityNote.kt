package ru.mokita.together.domain.model

import ru.mokita.together.data.source.network.model.DataUser

data class CommunityNote(
    val title: String = "",
    val id: String = "",
    val content: List<RichText> = emptyList(),
    val author: DataUser? = null,
    val date: String = "",
    val comments: List<Message> = emptyList()
)
