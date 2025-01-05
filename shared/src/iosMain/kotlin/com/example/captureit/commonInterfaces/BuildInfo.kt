package com.example.captureit.commonInterfaces

object BuildInfo {
    private lateinit var buildInfo: Map<String, Any>

    fun get(): Map<String, Any> {
        if (::buildInfo.isInitialized) {
            return buildInfo
        }

        buildInfo = getBuildInfoFromSwift()
        return buildInfo
    }

    private fun getBuildInfoFromSwift(): Map<String, Any> {
        return mapOf()
    }
}