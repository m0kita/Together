package ru.mokita.together.ui.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mokita.together.domain.model.CommunityNote
import ru.mokita.together.domain.model.Note
import ru.mokita.together.domain.model.RichText
import ru.mokita.together.domain.repository.CommunityNoteRepository
import ru.mokita.together.domain.repository.NoteRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

data class AddNoteUiState(
    val title: String = "",
    val description: String = "",
    val type: String = "local"
)

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val communityNoteRepository: CommunityNoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddNoteUiState())
    val uiState = _uiState.asStateFlow()
    private val date =  getCurrentDate()

    fun createNote()  {
        viewModelScope.launch {
        if(uiState.value.type == "local") {
            val note = Note(
                title = uiState.value.title,
                description = uiState.value.description,
                date = date
            )
            noteRepository.insert(note)
        } else {
            val state = uiState.value
            val  richText = RichText(text = state.description)
            communityNoteRepository.createCommunityNote(title = state.title, content = listOf(richText))
        }
        }
    }

    fun updateTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun updateDescription(description: String) {
        _uiState.update {
            it.copy(description = description)
        }
    }

    fun updateType(newType: String) {
        _uiState.update {
            it.copy(type = newType)
        }
    }

    private fun getCurrentDate(): String {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("d MMMM", Locale("ru", "RU"))
        return dateFormat.format(currentDate)
    }
}