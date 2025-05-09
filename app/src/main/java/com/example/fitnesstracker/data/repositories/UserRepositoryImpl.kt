package com.example.fitnesstracker.data.repositories

import com.example.fitnesstracker.domain.UserRepository
import com.example.fitnesstracker.domain.entities.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository{
    private val users = mutableListOf<User>()

    override fun registerUser(user: User) {
        if (users.any {it.login == user.login}) {
            throw Exception("Error with registration")
        }
        else {
            users.add(user)
        }
    }

    override fun loginUser(login: String, password: String): User? {
        return users.find { it.login == login && it.password == password}
    }
}