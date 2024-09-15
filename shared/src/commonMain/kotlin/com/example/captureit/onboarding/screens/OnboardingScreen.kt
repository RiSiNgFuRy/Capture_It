package com.example.captureit.onboarding.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.serialization.Serializable


@Composable
fun OnboardingScreen(
    navController: NavController
) {
    Box(
       modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Welcome to first Launch Screen"
        )
    }
}

@Serializable
object OnboardingScreen