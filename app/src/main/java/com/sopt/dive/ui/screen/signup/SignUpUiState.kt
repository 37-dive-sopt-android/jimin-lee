package com.sopt.dive.ui.screen.signup

data class SignUpUiState(
    val userUName: String = "",
    val userPw: String = "",
    val userName: String = "",
    val userEmail: String = "",
    val userAge: String = "",
    val idError: String = "",
    val pwError: String = "",
    val nickError: String = "",
    val emailError: String = "",
    val ageError: String = "",
)
