package ru.mokita.together.domain.model

data class User(
    val id: String = "",
    val name: String = "",
    val surname: String = "",
    val avatar: String = "",
    val role: String = "",
    val phone: String = "",
    val passwordHashed: String = ""
)
