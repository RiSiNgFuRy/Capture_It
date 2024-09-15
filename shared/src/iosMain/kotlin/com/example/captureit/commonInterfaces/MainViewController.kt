package com.example.captureit.commonInterfaces

import androidx.compose.ui.window.ComposeUIViewController
import com.example.captureit.App
import com.example.captureit.services.injections.initKoin
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin {
            modules(platformModules())
        }
    }
) {
    App()
}