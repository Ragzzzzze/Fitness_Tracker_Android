package com.example.fitnesstracker.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.presentation.screens.ActivityScreen
import com.example.fitnesstracker.presentation.screens.LoginScreen
import com.example.fitnesstracker.presentation.screens.RegistrationScreen
import com.example.fitnesstracker.presentation.screens.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME
    ) {
        composable(Routes.WELCOME) {
            WelcomeScreen(
                onLoginClick = { navController.navigate(Routes.LOGIN) },
                onRegisterClick = { navController.navigate(Routes.REGISTER) }
            )
        }
        composable(Routes.LOGIN) {
            LoginScreen(
                onBackClick = { navController.popBackStack() },
                onLoginSuccess = {
                    navController.navigate(Routes.ACTIVITY_SCREEN) {
                        popUpTo(Routes.WELCOME) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.REGISTER) {
            RegistrationScreen(
                onBackClick = { navController.popBackStack() },
                onRegistrationSuccess = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.WELCOME)
                    }
                }
            )
        }
        composable(Routes.ACTIVITY_SCREEN) {
            ActivityScreen()
        }
    }
}
