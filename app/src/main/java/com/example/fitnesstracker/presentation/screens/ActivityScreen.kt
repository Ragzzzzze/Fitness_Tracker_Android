package com.example.fitnesstracker.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.presentation.viewmodels.ActivityViewModel
import com.example.fitnesstracker.R
import com.example.fitnesstracker.domain.entities.Activity
import com.example.fitnesstracker.presentation.ui.components.ActivityCard
import com.example.fitnesstracker.presentation.ui.components.GrayText
import com.example.fitnesstracker.presentation.ui.components.LargeText
import com.example.fitnesstracker.presentation.ui.theme.Primary
import com.example.fitnesstracker.presentation.ui.theme.White
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.fitnesstracker.presentation.ui.theme.Line
import com.example.fitnesstracker.res.AppStrings.Companion


data class NavItem(
    val label: String,
    val icon: Painter
)

@Composable
fun ActivityScreen(
    viewModel: ActivityViewModel = hiltViewModel(),
    currentUserName: String = "example"
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTopTab by remember { mutableStateOf(0) }
    var selectedBottomTab by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.loadActivities(currentUserName)
    }

    val navItemList = listOf(
        NavItem(
            "AppStrings.ACTIVITY",
            painterResource(id = R.drawable.activity)
        ),
        NavItem(
            "AppStrings.PROFILE",
            painterResource(id = R.drawable.person)
        )
    )

    Scaffold(
        containerColor = Line,
        topBar = {
            TabRow(
                selectedTabIndex = selectedTopTab,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTopTab]),
                        color = Primary,
                    )
                },
                containerColor = White,
                contentColor = Primary,

                ) {
                listOf(
                    Companion.ACTIVITY_SCREEN_MY,
                    Companion.ACTIVITY_SCREEN_OTHER
                ).forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTopTab == index,
                        onClick = { selectedTopTab = index },
                        text = { Text(title) }
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = White
            ) {
                navItemList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedBottomTab == index,
                        onClick = {
                            selectedBottomTab = index
                        },
                        icon = {
                            Icon(
                                painter = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = {
                            Text(
                                text = item.label
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Primary,
                            selectedTextColor = Primary,
                            indicatorColor = MaterialTheme.colorScheme.background
                        )
                    )
                }
            }
        },
        floatingActionButton = {
            if (selectedTopTab == 0 && selectedBottomTab == 0) {
                FloatingActionButton(
                    onClick = { /* Начать трекинг активности */ },
                    containerColor = Primary,
                    shape = CircleShape,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Start tracking"
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedBottomTab) {
                0 -> {
                    when (selectedTopTab) {
                        0 -> MyActivitiesContent(
                            activities = uiState.myActivities,
                            isLoading = uiState.isLoading
                        )
                        1 -> UsersActivitiesContent(
                            activities = uiState.usersActivities,
                            isLoading = uiState.isLoading
                        )
                    }
                }


                1 -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Экран профиля")
                    }
                }
            }
        }
    }
}

@Composable
fun MyActivitiesContent(activities: List<Activity>, isLoading: Boolean) {
    if (isLoading) {
        TODO()
    } else if (activities.isEmpty()) {
        EmptyState()
    } else {
        ActivityList(activities, isCurrentUser = true)
    }
}

@Composable
fun UsersActivitiesContent(activities: List<Activity>, isLoading: Boolean) {
    if (isLoading) {
        TODO()
    } else {
        ActivityList(activities, isCurrentUser = false)
    }
}

@Composable
fun ActivityList(activities: List<Activity>, isCurrentUser: Boolean) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(activities) { activity ->
            ActivityCard(
                activity = activity,
                isCurrentUser = isCurrentUser,
            )
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LargeText(
            text = Companion.ACTIVITY_SCREEN_TRAIN
        )
        GrayText(
            text = Companion.ACTIVITY_SCREEN_LETS,
        )

    }
}

