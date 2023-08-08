package ru.mokita.together.data.source.network.model

data class UserRegistrationRequest(
    val name: String,
    val surname: String,
    val avatar: String?,
    val phone: String,
    val passwordHashed: String
)
