package com.example.fitnesstracker.presentation.state

sealed class RegistrationUiEvent {
    data class LoginChanged(val value: String) : RegistrationUiEvent()
    data class NameChanged(val value: String) : RegistrationUiEvent()
    data class PasswordChanged(val value: String) : RegistrationUiEvent()
    data class RepeatedPasswordChanged(val value: String) : RegistrationUiEvent()
    data class GenderChanged(val value: String) : RegistrationUiEvent()
    object Submit : RegistrationUiEvent()
}