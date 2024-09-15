package com.example.captureit.onboarding.repos

import com.example.captureit.services.network.NetworkService
import com.example.captureit.services.network.Response
import com.example.captureit.onboarding.interfaces.ILoginSignUpRepo
import com.example.captureit.onboarding.models.LoginResponse
import com.example.captureit.onboarding.models.SignUpRequest
import com.example.captureit.onboarding.models.SignUpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class LoginSignUpRepoImpl(
    private val networkService: NetworkService
): ILoginSignUpRepo {

    override suspend fun loginUser(mobileNumber: String): Flow<Response<LoginResponse>> {
        return flow {
            val response = networkService.post<String, LoginResponse>(
                path = "/login",
                requestBody = mobileNumber
            )
            emit(response)
        }.onStart { emit(Response.Loading) }
    }

    override suspend fun signUpUser(
        signUpRequest: SignUpRequest
    ): Flow<Response<SignUpResponse>> {
        return flow {
            val response = networkService.post<SignUpRequest, SignUpResponse>(
                path = "/signup",
                requestBody = signUpRequest
            )
            emit(response)
        }.onStart { emit(Response.Loading) }
    }

}