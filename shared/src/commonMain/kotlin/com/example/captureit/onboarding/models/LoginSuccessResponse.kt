package com.example.captureit.onboarding.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginSuccessResponse(
    @SerialName("user")
    val user: User?,

    @SerialName("tid")
    val tid: String?,

    @SerialName("sid")
    val sid: String?,
)