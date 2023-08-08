package ru.mokita.together.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mokita.together.domain.model.Note
import ru.mokita.together.domain.repository.NoteRepository
import javax.inject.Inject

data class NoteUiState(
    val notes: List<Note> = emptyList(),
)

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState = _uiState.asStateFlow()

    private fun updateNotes(newNotes: List<Note>) {
        _uiState.update {
            it.copy(notes = newNotes)
        }
    }

    private fun loadNotes() {
        viewModelScope.launch {
            val notes = noteRepository.getAllNotes()
            updateNotes(notes)
        }
    }

    init {
        loadNotes()
    }
}