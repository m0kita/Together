package ru.mokita.together.data.repository

import ru.mokita.together.data.source.network.api.TogetherApi
import ru.mokita.together.data.toUi
import ru.mokita.together.domain.model.Course
import ru.mokita.together.domain.repository.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val networkSource: TogetherApi
): CourseRepository {
    override suspend fun getCourses(): List<Course> {
        val response = networkSource.getCourses()
        return response.data?.toUi() ?: emptyList()
    }
}