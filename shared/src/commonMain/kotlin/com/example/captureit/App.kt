package com.example.captureit

import androidx.compose.runtime.Composable
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import com.example.captureit.commonHelpers.getAsyncImageLoader
import com.example.captureit.services.navigation.initializeNavController
import com.example.captureit.theme.CaptureItTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App() {
    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }
    CaptureItTheme {
        initializeNavController()
    }
}
