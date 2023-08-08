package ru.mokita.together.data.repository

import ru.mokita.together.data.source.network.api.TogetherApi
import ru.mokita.together.data.source.network.model.Authentification
import ru.mokita.together.data.source.network.model.Response
import ru.mokita.together.data.source.network.model.UserLoginRequest
import ru.mokita.together.data.source.network.model.UserRegistrationRequest
import ru.mokita.together.domain.repository.AuthentificationRepository
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import javax.inject.Inject

class AuthentificationRepositoryImpl @Inject constructor(
    private val networkSource: TogetherApi
) : AuthentificationRepository {
    override suspend fun login(phone: String, password: String): Response<Authentification> {
        val request = UserLoginRequest(
            phone = phone,
            passwordHashed = hashPassword(password)
        )
        return networkSource.login(request)
    }

    override suspend fun registration(
        name: String,
        surname: String,
        avatar: String,
        phone: String,
        password: String
    ): Response<Authentification> {
        val request = UserRegistrationRequest(
            name = name,
            surname = surname,
            avatar = avatar,
            phone = phone,
            passwordHashed = hashPassword(password)
        )
        return networkSource.registration(request)
    }

    fun hashPassword(password: String): String {
        val messageDigest = MessageDigest.getInstance("MD5")
        val hashBytes = messageDigest.digest(password.toByteArray())
        val bigInteger = BigInteger(1, hashBytes)
        var hashedPassword = bigInteger.toString(16)
        while (hashedPassword.length < 32) {
            hashedPassword = "0$hashedPassword"
        }
        return hashedPassword
    }
}