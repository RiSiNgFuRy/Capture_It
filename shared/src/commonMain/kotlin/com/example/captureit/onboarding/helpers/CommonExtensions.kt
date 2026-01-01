package com.example.captureit.onboarding.helpers

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.captureit.onboarding.navigation.OnboardingScreens
import com.example.captureit.utils.StringUtils

fun OnboardingScreens.route(): String {
    return this::class.qualifiedName ?: StringUtils.EMPTY
}

@Composable
fun NavHostController.route(): String {
    return this.currentBackStackEntryAsState().value?.destination?.route ?: StringUtils.EMPTY
}