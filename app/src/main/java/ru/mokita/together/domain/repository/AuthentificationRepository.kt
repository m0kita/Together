package ru.mokita.together.domain.repository

import ru.mokita.together.data.source.network.model.Authentification
import ru.mokita.together.data.source.network.model.Response

interface AuthentificationRepository {

    suspend fun login(phone: String, password: String): Response<Authentification>

    suspend fun registration(name: String, surname: String, avatar: String = "", phone: String, password: String): Response<Authentification>
}