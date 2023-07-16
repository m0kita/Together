package ru.mokita.profnotes.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.mokita.profnotes.ui.model.Course

data class MainUiState(
    val courses: List<Course> = emptyList()
)

class MainViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

}