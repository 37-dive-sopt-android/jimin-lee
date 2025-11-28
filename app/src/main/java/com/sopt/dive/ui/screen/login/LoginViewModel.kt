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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val loginService by lazy { ServicePool.authService }

    private val _loginState = MutableStateFlow<UiState<BaseResponse<ResponseLoginDto>?>>(UiState.Loading)
    val loginState: StateFlow<UiState<BaseResponse<ResponseLoginDto>?>> = _loginState.asStateFlow()

    fun postLogin(request: RequestLoginDto) {
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            try {
                val response = loginService.postLogin(request)
                if (response.isSuccessful) {
                    _loginState.value = UiState.Success(response.body())
                } else {
                    _loginState.value = UiState.Failure("${response.code()} ${response.message()}")
                    Log.e("error", "${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _loginState.value = UiState.Failure(e.message ?: "${e.message}")
            }
        }
    }
}
