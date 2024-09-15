package com.example.captureit.commonInterfaces

import io.ktor.client.HttpClient

interface Network {
    val client: HttpClient
}

expect fun getNetwork(
    extraHeadersMap: HashMap<String, String> = hashMapOf()
): Network