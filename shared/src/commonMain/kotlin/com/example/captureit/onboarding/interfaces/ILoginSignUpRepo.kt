package com.example.captureit.onboarding.interfaces

import com.example.captureit.services.network.Response
import com.example.captureit.onboarding.models.LoginResponse
import com.example.captureit.onboarding.models.SignUpRequest
import com.example.captureit.onboarding.models.SignUpResponse
import kotlinx.coroutines.flow.Flow

interface ILoginSignUpRepo {
    suspend fun loginUser(mobileNumber: String): Flow<Response<LoginResponse>>

    suspend fun signUpUser(signUpRequest: SignUpRequest) :Flow<Response<SignUpResponse>>
}