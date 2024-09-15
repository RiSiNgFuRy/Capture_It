package com.example.captureit.services.injections

import com.example.captureit.commonInterfaces.platformModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule(), platformModules())
    }
}