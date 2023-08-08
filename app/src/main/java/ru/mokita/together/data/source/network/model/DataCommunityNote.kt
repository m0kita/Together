package ru.mokita.together.data.source.network.model

data class DataCommunityNote(
    val title: String,
    val id: String,
    val content: List<DataRichText>,
    val author: DataUser,
    val date: String,
    val comments: List<DataMessage>
)
