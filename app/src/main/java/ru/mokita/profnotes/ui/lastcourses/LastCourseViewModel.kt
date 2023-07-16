package ru.mokita.profnotes.ui.lastcourses

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.mokita.profnotes.ui.model.Course

data class LastCoursesUiState(
    val courseName: String = "",
    val courseThemes: List<String> = emptyList()
)

class LastCourseViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<LastCoursesUiState> =
        MutableStateFlow(LastCoursesUiState())
    val uiState: StateFlow<LastCoursesUiState> = _uiState.asStateFlow()

    fun updateCourse(course: Course) {
        _uiState.update {
            it.copy(courseName = course.courseName, courseThemes = course.courseThemes)
        }
    }
}