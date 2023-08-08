package ru.mokita.together.domain.repository

import ru.mokita.together.domain.model.User

interface UserRepository {
    fun getUser(): User

    fun getUsers(): List<User>
}