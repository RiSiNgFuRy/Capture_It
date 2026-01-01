package com.example.captureit.services.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.captureit.onboarding.screens.LoginScreen
import com.example.captureit.onboarding.screens.OnboardingScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun App() {
    val navController = rememberNavController()

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = OnboardingScreen
        ) {
            composable<OnboardingScreen> {
                OnboardingScreen(
                    baseNavController = navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }

            composable<LoginScreen> {
                LoginScreen(
                    baseNavHostController = navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
        }
    }
}