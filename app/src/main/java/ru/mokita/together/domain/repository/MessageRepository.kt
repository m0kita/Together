package ru.mokita.together.domain.repository

import ru.mokita.together.domain.model.Message

interface MessageRepository {
    fun getMessages(): List<Message>
}