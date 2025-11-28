package com.sopt.dive.ui.screen.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

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
