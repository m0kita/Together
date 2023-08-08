package ru.mokita.together.data.source.network.model

data class DataMessage(
    val id: String,
    val author: DataUser,
    val text: String,
    val attachments: List<String>,
    val status: String
)
