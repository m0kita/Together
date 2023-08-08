package ru.mokita.together.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mokita.together.data.source.local.SharedPreferencesHelper
import ru.mokita.together.domain.repository.AuthentificationRepository
import javax.inject.Inject

data class LoginUiState(
    val phone: String = "",
    val password: String = "",
    val isTokenValid: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authentificationRepository: AuthentificationRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun updatePhone(newPhone: String) {
        _uiState.update {
            it.copy(phone = newPhone)
        }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update {
            it.copy(password = newPassword)
        }
    }

    private fun updateTokenValid(newValue: Boolean) {
        _uiState.update {
            it.copy(isTokenValid = newValue)
        }
    }

    fun login() {
        val state = uiState.value
        viewModelScope.launch {
            val response = authentificationRepository.login(phone = state.phone, password = state.password)
            if(response.data != null) {
                val token = response.data.token
                sharedPreferencesHelper.setToken(token)
                updateTokenValid(true)
            }
        }
    }
}