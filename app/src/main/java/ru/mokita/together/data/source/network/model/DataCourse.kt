package ru.mokita.together.data.source.network.model

data class DataCourse(
    val title: String,
    val tags: List<String>,
    val status: String,
    val plannedDate: String,
    val description: String,
    val text: List<DataRichText>
)