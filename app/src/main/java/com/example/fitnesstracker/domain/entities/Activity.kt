package com.example.fitnesstracker.domain.entities

import com.example.fitnesstracker.res.AppStrings.Companion

data class Activity(
    val userName: String,
    val distance: String,
    val duration: String,
    val activityType: String,
    val timestamp: Long = System.currentTimeMillis(),
    val userTag: String? = null
) {
    fun getTimeAgo(): String {
        val diff = (System.currentTimeMillis() - timestamp) / 1000
        return when {
            diff < 60 -> "$diff ${Companion.ACTIVITY_CARD_SECS_AGO}"
            diff < 3600 -> "${diff/60} ${Companion.ACTIVITY_CARD_MINS_AGO}"
            diff < 86400 -> "${diff/3600} ${Companion.ACTIVITY_CARD_HOURS_AGO}"
            else -> "${diff/86400} ${Companion.ACTIVITY_CARD_DAYS_AGO}"
        }
    }
}
