package com.example.captureit.commonInterfaces

interface IAppLogger {
    fun d(classTag: String, msg: String)
    fun d(classTag: String, msg: String, throwable: Throwable)
    fun logException(classTag: String, throwable: Throwable)
}

expect fun getAppLogger(): IAppLogger

