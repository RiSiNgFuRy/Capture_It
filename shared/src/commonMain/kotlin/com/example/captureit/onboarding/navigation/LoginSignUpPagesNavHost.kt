package com.example.captureit.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.captureit.onboarding.screens.OtpPage
import com.example.captureit.onboarding.screens.PhoneNumberPage

@Composable
fun LoginSignUpPagesNavHost(
    modifier: Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = PhoneNumberPage
    ) {
        composable<PhoneNumberPage> {
            PhoneNumberPage()
        }

        composable<OtpPage> {
            OtpPage()
        }
    }
}