package com.example.captureit.commonInterfaces

import android.util.Log
import com.example.captureit.BuildConfig

class AndroidLogger: IAppLogger {
    private val msgFormat: String = "%s|%s|%s\n%s"
    private val manualIdentifier = "logging manual exception"

    override fun d(classTag: String, msg: String) {
      if (BuildConfig.IS_LOGGING_ENABLED) {
        Log.d(classTag, msg)
      }
    }

    override fun d(classTag: String, msg: String, throwable: Throwable) {
      if (BuildConfig.IS_LOGGING_ENABLED) {
        Log.d(
          classTag,
          String.format(msgFormat, Thread.currentThread().name, classTag, manualIdentifier, msg),
          throwable
        )
      }
    }

    override fun logException(classTag: String, throwable: Throwable) {
      if (BuildConfig.IS_LOGGING_ENABLED) {
        Log.e(
          BuildConfig.LIBRARY_PACKAGE_NAME,
          String.format(msgFormat, Thread.currentThread().name, classTag, manualIdentifier),
          throwable
        )
      }
    }
}

actual fun getAppLogger(): IAppLogger {
 return AndroidLogger()
}