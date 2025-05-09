package com.example.fitnesstracker.presentation.state

sealed class LoginUiEvent {
    data class LoginChanged(val value: String) : LoginUiEvent()
    data class PasswordChanged(val value: String) : LoginUiEvent()
    object Submit : LoginUiEvent()
}
