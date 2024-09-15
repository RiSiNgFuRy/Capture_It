package com.example.captureit.utils

sealed class ContentState<out T> {
    data object InitialState: ContentState<Nothing>()
    data object LoadingState: ContentState<Nothing>()
    data class SuccessState<T>(val data: T): ContentState<T>()
    data class ErrorState   (val errorMsg: String): ContentState<Nothing>()
}