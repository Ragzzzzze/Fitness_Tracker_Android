package com.example.fitnesstracker.data.repositories

import com.example.fitnesstracker.domain.ActivityRepository
import com.example.fitnesstracker.domain.entities.Activity
import javax.inject.Inject


class ActivityRepositoryImpl @Inject constructor() : ActivityRepository {
    private val activities = mutableListOf<Activity>()


    override fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    override fun getUserActivities(userName: String): List<Activity> {
        return activities.filter { it.userName == userName }
    }

    override fun getAllActivities(): List<Activity> {
        return activities.sortedByDescending { it.timestamp }
    }
}
