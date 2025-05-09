package com.example.fitnesstracker.data.repositories

import com.example.fitnesstracker.domain.UserRepository
import com.example.fitnesstracker.domain.dao.UserDao
import com.example.fitnesstracker.domain.entities.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun registerUser(user: User) {
        if (userDao.getUserByLogin(user.login) != null) {
            throw Exception("Пользователь с таким логином уже существует")
        }
        userDao.insertUser(user)
    }

    override suspend fun loginUser(login: String, password: String): User? {
        return userDao.getUser(login, password)
    }
}
