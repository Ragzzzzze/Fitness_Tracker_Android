package com.example.fitnesstracker.domain

import com.example.fitnesstracker.domain.entities.Activity

interface ActivityRepository {
    fun addActivity(activity: Activity)
    fun getUserActivities(userId: String): List<Activity>
    fun getAllActivities(): List<Activity>
}
