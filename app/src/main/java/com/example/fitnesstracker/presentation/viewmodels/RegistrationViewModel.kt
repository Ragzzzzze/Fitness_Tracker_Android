package com.example.fitnesstracker.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.domain.UserRepository
import com.example.fitnesstracker.domain.entities.User
import com.example.fitnesstracker.presentation.state.RegistrationUiEvent
import com.example.fitnesstracker.presentation.state.RegistrationUiState
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
class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState.asStateFlow()

    private val _events = Channel<RegistrationUiEvent>()
    private val events = _events.receiveAsFlow()

    init {
        viewModelScope.launch {
            events.collect { handleEvent(it) }
        }
    }

    fun sendEvent(event: RegistrationUiEvent) {
        viewModelScope.launch {
            _events.send(event)
        }
    }

    private fun handleEvent(event: RegistrationUiEvent) {
        when (event) {
            is RegistrationUiEvent.LoginChanged -> {
                _uiState.update { it.copy(login = event.value) }
            }

            is RegistrationUiEvent.NameChanged -> {
                _uiState.update { it.copy(name = event.value) }
            }

            is RegistrationUiEvent.PasswordChanged -> {
                _uiState.update { it.copy(password = event.value) }
            }

            is RegistrationUiEvent.RepeatedPasswordChanged -> {
                _uiState.update { it.copy(repeatedPassword = event.value) }
            }

            is RegistrationUiEvent.GenderChanged -> {
                _uiState.update { it.copy(gender = event.value) }
            }

            RegistrationUiEvent.Submit -> {
                register()
            }
        }
    }

    private fun register() {
        val currentState = _uiState.value

        if (currentState.password != currentState.repeatedPassword) {
            _uiState.update { it.copy(error = "Пароли не совпадают") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val user = User(
                    login = currentState.login,
                    name = currentState.name,
                    password = currentState.password,
                    gender = currentState.gender
                )

                userRepository.registerUser(user)
                _uiState.update { it.copy(registrationSuccess = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

}
