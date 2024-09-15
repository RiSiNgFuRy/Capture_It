package com.example.captureit.android

import android.app.Application
import com.example.captureit.services.injections.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class CaptureItApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@CaptureItApplication)
        }
    }
}