package com.example.captureit.services.network

import com.example.captureit.commonInterfaces.getNetwork
import com.example.captureit.utils.StringUtils
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.util.InternalAPI

class NetworkService {
    val network by lazy { getNetwork() }

    @OptIn(InternalAPI::class)
    suspend inline fun <T : Any, reified R : Any> post(
        path: String = StringUtils.EMPTY,
        queries: HashMap<String, String> = hashMapOf(),
        requestBody: T
    ): Response<R> {
        try {
            val res = network.client.post("BuildConfig.BASE_URL" + path) {
                queries.entries.forEach {
                    parameter(it.key, it.value)
                }
                body = requestBody
            }
            return Response.Success(res.body())
        } catch (e: Exception) {
            e.printStackTrace()
            return Response.Error(e.message ?: StringUtils.EMPTY)
        }
    }

    suspend inline fun <reified R: Any> get(
        path: String = StringUtils.EMPTY,
        queries: HashMap<String, String> = hashMapOf()
    ): Response<R> {
        try {
            val res = network.client.get("BuildConfig.BASE_URL" + path) {
                queries.entries.forEach {
                    parameter(it.key, it.value)
                }
            }
            return Response.Success(res.body())
        } catch (e: Exception) {
            e.printStackTrace()
            return Response.Error(e.message ?: StringUtils.EMPTY)
        }
    }
}