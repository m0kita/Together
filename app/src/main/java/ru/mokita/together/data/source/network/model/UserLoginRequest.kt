package ru.mokita.together.data.source.network.model

data class UserLoginRequest(
    val phone: String,
    val passwordHashed: String
)
