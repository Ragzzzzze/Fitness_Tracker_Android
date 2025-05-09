package com.example.fitnesstracker.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.domain.ActivityRepository
import com.example.fitnesstracker.domain.UserRepository
import com.example.fitnesstracker.domain.entities.Activity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val activityRepository: ActivityRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ActivityUiState())
    val uiState: StateFlow<ActivityUiState> = _uiState.asStateFlow()

    init {
        fillTestData()
    }

    fun loadActivities(currentUserName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val myActivities = activityRepository.getUserActivities(currentUserName)
                val allActivities = activityRepository.getAllActivities()
                    .filter { it.userName != currentUserName }

                _uiState.update {
                    it.copy(
                        myActivities = myActivities,
                        usersActivities = allActivities,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun addActivity(
        userName: String,
        userTag: String?,
        distance: String,
        duration: String,
        activityType: String
    ) {
        viewModelScope.launch {
            val activity = Activity(
                userName = userName,
                userTag = userTag,
                distance = distance,
                duration = duration,
                activityType = activityType
            )
            activityRepository.addActivity(activity)
            loadActivities(userName)
        }
    }

    fun fillTestData() {
        viewModelScope.launch {
            val activities = listOf(
                Activity(userName = "user1", distance = "5 км", duration = "30 мин", activityType = "Бег", userTag = "runner"),
                Activity(userName = "user1", distance = "10 км", duration = "45 мин", activityType = "Велоспорт", userTag = "runner"),
                Activity(userName = "user2", distance = "3 км", duration = "25 мин", activityType = "Ходьба", userTag = "walker"),
                Activity(userName = "Ragzzzzze", distance = "7.5 км", duration = "40 мин", activityType = "Плавание")
            )

            activities.forEach { activityRepository.addActivity(it) }
            loadActivities("current_user")
        }
    }
}

data class ActivityUiState(
    val myActivities: List<Activity> = emptyList(),
    val usersActivities: List<Activity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
