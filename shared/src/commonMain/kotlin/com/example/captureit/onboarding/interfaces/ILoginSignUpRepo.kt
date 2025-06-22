package com.example.captureit.onboarding.interfaces

import com.example.captureit.onboarding.models.LoginSuccessResponse
import com.example.captureit.onboarding.models.PhoneNumberResponse
import com.example.captureit.onboarding.models.SignUpRequest
import com.example.captureit.onboarding.models.SignUpResponse
import com.example.captureit.services.network.Response
import kotlinx.coroutines.flow.Flow

interface ILoginSignUpRepo {
    suspend fun loginUser(mobileNumber: String): Flow<Response<PhoneNumberResponse>>

    suspend fun signUpUser(signUpRequest: SignUpRequest) :Flow<Response<SignUpResponse>>

    suspend fun verifyOtp(code: String): Flow<Response<LoginSuccessResponse>>
}