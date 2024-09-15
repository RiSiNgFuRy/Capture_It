package com.example.captureit.onboarding.viewmodels

import com.example.captureit.onboarding.models.LoginResponse
import com.example.captureit.onboarding.usecases.LoginServiceUseCase
import com.example.captureit.services.BaseViewModel
import com.example.captureit.utils.ContentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    private val loginServiceUseCase: LoginServiceUseCase
): BaseViewModel() {
    private val _loginUserState = MutableStateFlow<ContentState<LoginResponse>>(ContentState.InitialState)
    val loginUserState: StateFlow<ContentState<LoginResponse>>
        get() = _loginUserState.asStateFlow()

    fun loginUser(mobileNumber: String) {
        vmIOScopeLaunchWithErrorHandling {
            getContentStateAsFlow(loginServiceUseCase.invoke(mobileNumber))
                .collect { contentState -> _loginUserState.update { contentState } }
        }
    }

}