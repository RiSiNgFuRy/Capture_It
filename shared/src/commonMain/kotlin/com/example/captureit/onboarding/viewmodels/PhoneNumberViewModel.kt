package com.example.captureit.onboarding.viewmodels

import com.example.captureit.onboarding.models.PhoneNumberResponse
import com.example.captureit.onboarding.usecases.PhoneNumberServiceUseCase
import com.example.captureit.services.BaseViewModel
import com.example.captureit.utils.ContentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PhoneNumberViewModel(
    private val phoneNumberServiceUseCase: PhoneNumberServiceUseCase
): BaseViewModel() {
    private val _loginUserState = MutableStateFlow<ContentState<PhoneNumberResponse>>(ContentState.InitialState)
    val loginUserState: StateFlow<ContentState<PhoneNumberResponse>>
        get() = _loginUserState.asStateFlow()

    fun loginUser(mobileNumber: String) {
        vmIOScopeLaunchWithErrorHandling {
            getContentStateAsFlow(phoneNumberServiceUseCase.invoke(mobileNumber))
                .collect { contentState -> _loginUserState.update { contentState } }
        }
    }
}