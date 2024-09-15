package com.example.captureit.services.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.captureit.onboarding.screens.OnboardingScreen

@Composable
fun initializeNavController() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OnboardingScreen
    ) {
        composable<OnboardingScreen> {
            OnboardingScreen(navController)
        }
    }
}