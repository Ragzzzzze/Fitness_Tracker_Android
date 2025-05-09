package com.example.fitnesstracker.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey val login: String,
    var name: String,
    var password: String,
    var gender: String,
)