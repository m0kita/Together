package ru.mokita.together.data.source.network.model

data class DataUser(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String?,
    val role: String,
    val phone: String,
    val passwordHashed: String
)
