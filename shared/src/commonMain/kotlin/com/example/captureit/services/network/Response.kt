package com.example.captureit.services.network

sealed class Response<out T> {
    data class Success<T>(val data: T): Response<T>()
    data class Error(val errorMsg: String): Response<Nothing>()
    data object Loading: Response<Nothing>()
}