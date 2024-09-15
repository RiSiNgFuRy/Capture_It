package com.example.captureit.onboarding.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest (
    @SerialName("mobileNumber")
    val mobileNumber: String,

    @SerialName("emailAddress")
    val emailAddress: String,
)