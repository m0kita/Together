package ru.mokita.together.domain.model

data class Course(
    val title: String = "",
    val tags: List<String> = emptyList()
)
