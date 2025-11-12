package com.sopt.dive.data.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)