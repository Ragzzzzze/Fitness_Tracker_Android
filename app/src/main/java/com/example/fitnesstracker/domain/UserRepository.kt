package com.example.fitnesstracker.domain

import com.example.fitnesstracker.domain.entities.User

interface UserRepository {
    fun registerUser (user: User)
    fun loginUser (login: String, password: String) : User?
}