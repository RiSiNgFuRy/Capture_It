package com.example.captureit.onboarding.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse (
    @SerialName("token")
    val token: String
)