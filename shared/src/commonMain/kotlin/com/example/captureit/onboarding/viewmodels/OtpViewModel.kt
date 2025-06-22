package com.example.captureit.onboarding.viewmodels

import com.example.captureit.onboarding.models.LoginSuccessResponse
import com.example.captureit.onboarding.usecases.OtpVerificationServiceUseCase
import com.example.captureit.services.BaseViewModel
import com.example.captureit.utils.ContentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OtpViewModel(
    private val otpVerificationServiceUseCase: OtpVerificationServiceUseCase
): BaseViewModel() {
    private val _otpVerificationState = MutableStateFlow<ContentState<LoginSuccessResponse>>(ContentState.InitialState)
    val otpVerificationState: StateFlow<ContentState<LoginSuccessResponse>>
        get() = _otpVerificationState.asStateFlow()

    fun verifyOtp(otpCode: String) {
        vmIOScopeLaunchWithErrorHandling {
            getContentStateAsFlow(otpVerificationServiceUseCase.invoke(otpCode))
                .collect { contentState -> _otpVerificationState.update { contentState } }
        }
    }
}