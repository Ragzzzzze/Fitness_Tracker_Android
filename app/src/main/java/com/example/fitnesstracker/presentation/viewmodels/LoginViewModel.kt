package com.example.fitnesstracker.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.domain.UserRepository
import com.example.fitnesstracker.presentation.state.LoginUiEvent
import com.example.fitnesstracker.presentation.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = Channel<LoginUiEvent>()
    val events = _events.receiveAsFlow()

    init {
        viewModelScope.launch {
            events.collect { handleEvent(it) }
        }
    }

    fun sendEvent(event: LoginUiEvent) {
        viewModelScope.launch {
            _events.send(event)
        }
    }

    private fun handleEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.LoginChanged -> {
                _uiState.update { it.copy(login = event.value) }
            }
            is LoginUiEvent.PasswordChanged -> {
                _uiState.update { it.copy(password = event.value) }
            }
            LoginUiEvent.Submit -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val user = userRepository.loginUser(
                    login = _uiState.value.login,
                    password = _uiState.value.password
                )

                if (user != null) {
                    _uiState.update { it.copy(loginSuccess = true) }
                } else {
                    _uiState.update { it.copy(error = "Неверный логин или пароль") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Ошибка авторизации: ${e.message}") }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
