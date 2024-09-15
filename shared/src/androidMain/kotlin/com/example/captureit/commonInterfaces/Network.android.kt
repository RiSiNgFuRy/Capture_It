package com.example.captureit.commonInterfaces

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json


@OptIn(ExperimentalSerializationApi::class)
class AndroidNetwork(
    extraHeadersMap: HashMap<String, String>
): Network {
    override val client = HttpClient(OkHttp) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(HttpTimeout) {
            socketTimeoutMillis = 6000
            requestTimeoutMillis = 6000
        }
        defaultRequest {
            header("Content-Type", "application/json")
            extraHeadersMap.entries.forEach {
                header(it.key, it.value)
            }
        }
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                }
            )
        }
    }
}

actual fun getNetwork(
    extraHeadersMap: HashMap<String, String>,
): Network = AndroidNetwork(extraHeadersMap)