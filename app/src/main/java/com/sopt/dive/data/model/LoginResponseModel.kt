package com.sopt.dive.data.model

import com.sopt.dive.data.dto.login.ResponseLoginDto
import kotlin.String

data class LoginResponseModel(
    val userId: Long,
    val message: String,
)

fun ResponseLoginDto.toModel() =
    LoginResponseModel(
        userId = this.userId,
        message = this.message
    )
