package ru.mokita.together.data.source.network.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.mokita.together.data.source.network.model.Authentification
import ru.mokita.together.data.source.network.model.CommunityNoteRequest
import ru.mokita.together.data.source.network.model.DataCommunityNote
import ru.mokita.together.data.source.network.model.DataCourse
import ru.mokita.together.data.source.network.model.Response
import ru.mokita.together.data.source.network.model.UserLoginRequest
import ru.mokita.together.data.source.network.model.UserRegistrationRequest

interface TogetherApi {

    @GET("courses")
    suspend fun getCourses(): Response<List<DataCourse>>

    @GET("community_notes")
    suspend fun getCommunityNotes(): Response<List<DataCommunityNote>>

    @POST("register")
    suspend fun registration(@Body userRegistrationRequest: UserRegistrationRequest): Response<Authentification>

    @POST("auth")
    suspend fun login(@Body userAuthentificationRequest: UserLoginRequest): Response<Authentification>

    @POST("community_notes")
    suspend fun createCommunityNote(@Body communityNoteRequest: CommunityNoteRequest): Response<DataCommunityNote>
}