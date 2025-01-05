package com.example.captureit.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.captureit.services.network.Response
import com.example.captureit.utils.ContentState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    private val vmScope = viewModelScope
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    fun vmIOScopeLaunch(execute: suspend CoroutineScope.() -> Unit): Job {
        return vmScope.launch(Dispatchers.IO) { execute() }
    }

    fun vmIOScopeLaunchWithErrorHandling(execute: suspend CoroutineScope.() -> Unit): Job {
        return vmScope.launch(Dispatchers.IO + coroutineExceptionHandler){ execute() }
    }

    fun <T>getContentStateAsFlow(
        flow: Flow<Response<T>>,
        onSuccess: ((data: T) -> T)? = null,
        onLoading: () -> Unit = {  },
        onError: ((message: String) -> String)? = null,
    ): Flow<ContentState<T>> {
         return flow.map { response ->
            when(response) {
                is Response.Success -> {
                    onSuccess?.let {
                        ContentState.SuccessState(it.invoke(response.data))
                    } ?: ContentState.SuccessState(response.data)
                }
                is Response.Loading -> {
                    onLoading.invoke()
                    ContentState.LoadingState
                }
                is Response.Error -> {
                    onError?.let {
                        ContentState.ErrorState(it.invoke(response.errorMsg))
                    } ?: ContentState.ErrorState(response.errorMsg)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmScope.coroutineContext.job.run {
            if (isActive) {
                this.cancel()
            }
        }
    }
}