package com.example.captureit.onboarding.usecases

import com.example.captureit.onboarding.interfaces.ILoginSignUpRepo

class OtpVerificationServiceUseCase(
    private val repo: ILoginSignUpRepo
) {
    suspend fun verifyOtp(code: String) = repo.verifyOtp(code)

    suspend fun resendOtp(mobileNumber: String) = repo.loginUser(mobileNumber)
}