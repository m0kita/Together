package ru.mokita.together.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mokita.together.domain.model.CommunityNote
import ru.mokita.together.domain.model.Course
import ru.mokita.together.domain.model.Note
import ru.mokita.together.domain.repository.CommunityNoteRepository
import ru.mokita.together.domain.repository.CourseRepository
import ru.mokita.together.domain.repository.NoteRepository
import ru.mokita.together.ui.main.MainUiState
import javax.inject.Inject

data class HomeUiState(
    val courses: List<Course> = emptyList(),
    val localNote: Note = Note(),
    val communityNote: CommunityNote = CommunityNote()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val courseRepository: CourseRepository,
    private val communityNoteRepository: CommunityNoteRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private fun updateLocalNote(localNote: Note) {
        _uiState.update {
            it.copy(localNote = localNote)
        }
    }

    private fun updateCourses(courses: List<Course>) {
        _uiState.update {
            it.copy(courses = courses)
        }
    }

    private fun updateCommunityNote(newCommunityNote: CommunityNote) {
        _uiState.update {
            it.copy(communityNote = newCommunityNote)
        }
    }

    private fun loadLocalNote() {
        viewModelScope.launch {
            val note = noteRepository.getNote()
            updateLocalNote(note)
        }
    }

    private fun loadCourses() {
        viewModelScope.launch {
            val courses = courseRepository.getCourses()
            updateCourses(courses)
        }
    }

    private fun loadCommunityNote() {
        viewModelScope.launch {
            val note = communityNoteRepository.getCommunityNote()
            updateCommunityNote(note)
        }
    }

    init {
        loadCourses()
        loadLocalNote()
        loadCommunityNote()
    }
}