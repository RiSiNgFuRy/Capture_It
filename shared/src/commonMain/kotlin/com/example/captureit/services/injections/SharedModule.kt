package com.example.captureit.services.injections

import com.example.captureit.onboarding.onboardingModules
import org.koin.dsl.module

fun sharedModule() = module {
    includes(onboardingModules())
}