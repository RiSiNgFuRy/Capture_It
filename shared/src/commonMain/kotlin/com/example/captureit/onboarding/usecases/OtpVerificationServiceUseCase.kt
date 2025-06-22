package com.example.captureit.onboarding.usecases

import com.example.captureit.onboarding.interfaces.ILoginSignUpRepo
import com.example.captureit.onboarding.models.LoginSuccessResponse
import com.example.captureit.services.network.Response
import kotlinx.coroutines.flow.Flow

class OtpVerificationServiceUseCase(
    private val repo: ILoginSignUpRepo
) {
    suspend operator fun invoke(code: String): Flow<Response<LoginSuccessResponse>> {
        return repo.verifyOtp(code)
    }
}