package com.example.captureit.onboarding.navigation

import kotlinx.serialization.Serializable

open class OnboardingScreens

@Serializable
object PhoneNumberPage: OnboardingScreens()

@Serializable
object OtpPage: OnboardingScreens()

@Serializable
object SignUpPage: OnboardingScreens()
