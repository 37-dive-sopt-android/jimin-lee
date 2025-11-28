package com.sopt.dive.ui.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.getValue
import com.sopt.dive.data.network.ServicePool
import com.sopt.dive.ui.common.UiState
import com.sopt.dive.data.dto.request.login.RequestLoginDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.login.ResponseLoginDto
import com.sopt.dive.data.dto.request.signup.RequestSignUpDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName

class LoginViewModel: ViewModel() {

    private val loginService by lazy { ServicePool.authService }

    private val _loginState = MutableStateFlow<UiState<ResponseLoginDto?>>(UiState.Loading)
    val loginState: StateFlow<UiState<ResponseLoginDto?>> = _loginState.asStateFlow()

    private val _loginInfo = MutableStateFlow(LoginUiState())
    val loginInfo: StateFlow<LoginUiState> = _loginInfo.asStateFlow()

    fun updateLoginId(id: String) {
        _loginInfo.update { it.copy(loginId = id) }
    }

    fun updateLoginPw(pw: String) {
        _loginInfo.update { it.copy(loginPw = pw) }
    }


    fun postLogin() {
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            val loginInfo = _loginInfo.value
            val request = RequestLoginDto(
                username = loginInfo.loginId,
                password = loginInfo.loginPw,
            )
            try {
                val response = loginService.postLogin(request)
                if (response.success) {
                    _loginState.value = UiState.Success(response.data)
                } else {
                    _loginState.value = UiState.Failure("${response.code} ${response.message}")
                    Log.e("error", "${response.code} ${response.message}")
                }
            } catch (e: Exception) {
                _loginState.value = UiState.Failure(e.message ?: "${e.message}")
            }
        }
    }
}
