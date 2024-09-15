package com.example.captureit.onboarding.usecases

import com.example.captureit.onboarding.models.LoginResponse
import com.example.captureit.services.network.Response
import com.example.captureit.onboarding.interfaces.ILoginSignUpRepo
import kotlinx.coroutines.flow.Flow

class LoginServiceUseCase(
    private val repo: ILoginSignUpRepo
) {
    suspend operator fun invoke(
        mobileNumber: String
    ): Flow<Response<LoginResponse>> = repo.loginUser(mobileNumber)
}
