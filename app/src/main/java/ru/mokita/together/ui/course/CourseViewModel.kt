package ru.mokita.together.ui.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mokita.together.domain.model.Course
import ru.mokita.together.domain.repository.CourseRepository
import javax.inject.Inject

data class CourseUiState(
    val courses: List<Course> = emptyList()
)

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(CourseUiState())
    val uiState = _uiState.asStateFlow()

    private fun updateCourses(newCourses: List<Course>) {
        _uiState.update {
            it.copy(courses = newCourses)
        }
    }

    private fun loadCourses() {
        viewModelScope.launch {
            val courses = courseRepository.getCourses()
            updateCourses(courses)
        }
    }

    init {
        loadCourses()
    }
}