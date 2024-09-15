package com.example.captureit.onboarding

import com.example.captureit.services.network.NetworkService
import com.example.captureit.onboarding.interfaces.ILoginSignUpRepo
import com.example.captureit.onboarding.repos.LoginSignUpRepoImpl
import com.example.captureit.onboarding.usecases.LoginServiceUseCase
import com.example.captureit.onboarding.usecases.SignUpServiceUseCase
import com.example.captureit.onboarding.viewmodels.LoginViewModel
import com.example.captureit.onboarding.viewmodels.SignUpViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun onboardingModules() = module {
    singleOf(::NetworkService)
    singleOf(::LoginSignUpRepoImpl) { bind<ILoginSignUpRepo>() }
    singleOf(::LoginServiceUseCase)
    singleOf(::SignUpServiceUseCase)
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignUpViewModel)
}