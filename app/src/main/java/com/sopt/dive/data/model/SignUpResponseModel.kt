package com.sopt.dive.data.model

import com.sopt.dive.data.dto.signup.ResponseSignUpDto
import kotlin.String

data class SignUpResponseModel(
    val id: Long,
    val username: String,
    val name: String,
    val email: String,
    val age: Int,
    val status: String
)

fun ResponseSignUpDto.toModel() =
    SignUpResponseModel(
        id = this.id,
        username = this.username,
        name = this.name,
        email = this.email,
        age = this.age,
        status = this.status
    )
