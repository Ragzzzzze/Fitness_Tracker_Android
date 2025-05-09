package com.example.fitnesstracker.presentation.state

import com.example.fitnesstracker.domain.entities.User

data class LoginUiState(
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val loginSuccess: Boolean = false,
    val currentUser: User? = null
)