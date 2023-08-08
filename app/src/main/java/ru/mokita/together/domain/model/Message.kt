package ru.mokita.together.domain.model

import ru.mokita.together.data.source.network.model.DataUser

data class Message(
    val id: String = "",
    val author: DataUser? = null,
    val text: String = "",
    val attachments: List<String> = emptyList(),
    val status: String = ""
)
