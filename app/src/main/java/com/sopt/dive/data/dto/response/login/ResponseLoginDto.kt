package com.sopt.dive.data.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    @SerialName("userId")
    val userId: Long,
    @SerialName("message")
    val message: String
)