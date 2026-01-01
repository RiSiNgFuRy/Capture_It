package com.example.captureit.onboarding

import com.example.captureit.onboarding.interfaces.ILoginSignUpRepo
import com.example.captureit.onboarding.repos.LoginSignUpRepoImpl
import com.example.captureit.onboarding.usecases.OtpVerificationServiceUseCase
import com.example.captureit.onboarding.usecases.PhoneNumberServiceUseCase
import com.example.captureit.onboarding.usecases.SignUpServiceUseCase
import com.example.captureit.onboarding.viewmodels.OnboardingViewModel
import com.example.captureit.onboarding.viewmodels.OtpViewModel
import com.example.captureit.onboarding.viewmodels.PhoneNumberViewModel
import com.example.captureit.onboarding.viewmodels.SignUpViewModel
import com.example.captureit.services.network.NetworkService
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun onboardingModules() = module {
    singleOf(::NetworkService)
    singleOf(::LoginSignUpRepoImpl) { bind<ILoginSignUpRepo>() }
    singleOf(::PhoneNumberServiceUseCase)
    singleOf(::OtpVerificationServiceUseCase)
    singleOf(::SignUpServiceUseCase)
    viewModelOf(::PhoneNumberViewModel)
    viewModelOf(::OtpViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::OnboardingViewModel)
}