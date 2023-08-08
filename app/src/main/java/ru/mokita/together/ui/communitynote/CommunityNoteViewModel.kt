package ru.mokita.together.ui.communitynote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mokita.together.domain.model.CommunityNote
import ru.mokita.together.domain.repository.CommunityNoteRepository
import javax.inject.Inject

data class CommunityNoteUiState(
    val notes: List<CommunityNote> = emptyList()
)

@HiltViewModel
class CommunityNoteViewModel @Inject constructor(
    private val communityNoteRepository: CommunityNoteRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(CommunityNoteUiState())
    val uiState = _uiState.asStateFlow()

    private fun updateNotes(newNotes: List<CommunityNote>) {
        _uiState.update {
            it.copy(notes = newNotes)
        }
    }

    private fun loadNotes() {
        viewModelScope.launch{
            val notes = communityNoteRepository.getCommunityNotes()
            updateNotes(notes)
        }
    }

    init {
        loadNotes()
    }
}