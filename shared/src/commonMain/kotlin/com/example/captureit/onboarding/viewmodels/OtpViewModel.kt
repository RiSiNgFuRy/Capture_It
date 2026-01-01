package com.example.captureit.onboarding.viewmodels

import com.example.captureit.onboarding.models.LoginSuccessResponse
import com.example.captureit.onboarding.models.PhoneNumberResponse
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

    private val _otpResendState = MutableStateFlow<ContentState<PhoneNumberResponse>>(ContentState.InitialState)
    val otpResendState: StateFlow<ContentState<PhoneNumberResponse>>
        get() = _otpResendState.asStateFlow()

    fun verifyOtp(otpCode: String) {
        vmIOScopeLaunchWithErrorHandling {
            getContentStateAsFlow(otpVerificationServiceUseCase.verifyOtp(otpCode))
                .collect { contentState -> _otpVerificationState.update { contentState } }
        }
    }

    fun resendOtp(mobileNumber: String) {
        vmIOScopeLaunchWithErrorHandling {
            getContentStateAsFlow(otpVerificationServiceUseCase.resendOtp(mobileNumber))
                .collect { contentState -> _otpResendState.update { contentState }}
        }
    }
}