package ru.mokita.together.domain.repository

import ru.mokita.together.domain.model.Course

interface CourseRepository {
    suspend fun getCourses(): List<Course>
}