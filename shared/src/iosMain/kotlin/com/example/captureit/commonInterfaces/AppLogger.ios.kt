package com.example.captureit.commonInterfaces

import platform.Foundation.NSLog

class IosLogger: IAppLogger {

    override fun d(classTag: String, msg: String) {
        NSLog("DEBUG: [$classTag] $msg")
    }

    override fun d(classTag: String, msg: String, throwable: Throwable) {
        NSLog("ERROR: [$classTag] $msg. Throwable: $throwable CAUSE ${throwable.cause}")
    }

    override fun logException(classTag: String, throwable: Throwable) {
        NSLog("ERROR: [$classTag]. Throwable: $throwable CAUSE ${throwable.cause}")
    }
}

actual fun getAppLogger(): IAppLogger {
    return IosLogger()
}
