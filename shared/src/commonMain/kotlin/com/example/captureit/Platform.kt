package com.example.captureit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform