package com.example.fitnesstracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitnesstracker.domain.dao.UserDao
import com.example.fitnesstracker.domain.entities.User


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}