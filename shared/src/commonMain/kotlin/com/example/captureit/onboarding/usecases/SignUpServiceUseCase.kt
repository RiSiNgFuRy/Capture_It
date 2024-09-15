package com.example.captureit.onboarding.usecases

import com.example.captureit.onboarding.models.SignUpRequest
import com.example.captureit.onboarding.models.SignUpResponse
import com.example.captureit.services.network.Response
import com.example.captureit.onboarding.interfaces.ILoginSignUpRepo
import kotlinx.coroutines.flow.Flow

class SignUpServiceUseCase(
    private val repo: ILoginSignUpRepo
) {
    suspend operator fun invoke(
        signUpRequest: SignUpRequest
    ): Flow<Response<SignUpResponse>> = repo.signUpUser(signUpRequest)
}