package com.example.fitnesstracker.presentation.state

data class RegistrationUiState (
    val login: String = "",
    val name: String = "",
    val password: String = "",
    val repeatedPassword: String = "",
    val gender: String = "",

    val isLoading: Boolean = false,
    val registrationSuccess: Boolean = false,
    val error: String? = null
)