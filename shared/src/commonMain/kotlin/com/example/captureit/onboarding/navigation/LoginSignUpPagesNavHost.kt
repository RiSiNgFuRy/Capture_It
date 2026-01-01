package com.example.captureit.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.captureit.onboarding.pages.OtpPage
import com.example.captureit.onboarding.pages.PhoneNumberPage
import com.example.captureit.onboarding.pages.SignUpPage
import com.example.captureit.onboarding.viewmodels.LoginSignupSharedViewModel

@Composable
fun LoginSignUpPagesNavHost(
    modifier: Modifier,
    navHostController: NavHostController,
) {
    val sharedViewModel = LoginSignupSharedViewModel()

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = PhoneNumberPage
    ) {
        composable<PhoneNumberPage> {
            PhoneNumberPage(sharedViewModel)
        }

        composable<OtpPage> {
            OtpPage(sharedViewModel)
        }

        composable<SignUpPage> {
            SignUpPage(sharedViewModel)
        }
    }
}