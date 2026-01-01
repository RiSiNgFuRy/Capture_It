package com.example.captureit.onboarding.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("name")
    val name: String?,

    @SerialName("phone_number")
    val phoneNumber: String?,

    @SerialName("customer_id")
    val customerId: String?,

    @SerialName("type")
    val type: String?,
)
