package ru.mokita.together.data.source.network.model

data class Response<T: Any>(
    val status: Int,
    val message: String?,
    val data: T?
)
