package com.example.captureit.onboarding.viewmodels

import com.example.captureit.onboarding.models.SignUpRequest
import com.example.captureit.onboarding.models.SignUpResponse
import com.example.captureit.onboarding.usecases.SignUpServiceUseCase
import com.example.captureit.services.BaseViewModel
import com.example.captureit.utils.ContentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel(
    private val signUpServiceUseCase: SignUpServiceUseCase
): BaseViewModel() {
    private val _signUpUserState = MutableStateFlow<ContentState<SignUpResponse>>(ContentState.InitialState)
    val signUpUserState: StateFlow<ContentState<SignUpResponse>>
        get() = _signUpUserState.asStateFlow()

    fun signUpUser(signUpRequest: SignUpRequest) {
        vmIOScopeLaunchWithErrorHandling {
            getContentStateAsFlow(signUpServiceUseCase.invoke(signUpRequest))
                .collect { contentState -> _signUpUserState.update { contentState } }
        }
    }

}