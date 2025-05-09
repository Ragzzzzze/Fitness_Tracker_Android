package com.example.fitnesstracker.domain

import com.example.fitnesstracker.domain.entities.User

interface UserRepository {
    suspend fun registerUser(user: User)
    suspend fun loginUser(login: String, password: String): User?
}