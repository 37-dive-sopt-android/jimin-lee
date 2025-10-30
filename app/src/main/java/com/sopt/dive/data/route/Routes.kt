package com.sopt.dive.data.route

import kotlinx.serialization.Serializable


@Serializable
data class Main(
    val id: String,
    val pw: String,
    val nickname: String,
    val mbti: String
)

@Serializable
data class Login(
    val id: String,
    val pw: String,
    val nickname: String,
    val mbti: String
)

@Serializable
data object SignUp

@Serializable
data object Home

@Serializable
data object Search

@Serializable
data object My