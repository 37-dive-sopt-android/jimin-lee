package com.sopt.dive.ui.screen.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.network.ServicePool
import com.sopt.dive.ui.common.UiState
import com.sopt.dive.data.dto.request.signup.RequestSignUpDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.signup.ResponseSignUpDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.getValue

class SignUpViewModel: ViewModel() {

    private val signupService by lazy { ServicePool.authService }

    private val _signupState = MutableStateFlow<UiState<BaseResponse<ResponseSignUpDto>?>>(UiState.Loading)
    val signupState: StateFlow<UiState<BaseResponse<ResponseSignUpDto>?>> = _signupState.asStateFlow()

    fun postSignUp(request: RequestSignUpDto) {
        viewModelScope.launch {
            _signupState.value = UiState.Loading
            try {
                val response = signupService.postSignUp(request)
                if (response.isSuccessful) {
                    _signupState.value = UiState.Success(response.body())
                } else {
                    _signupState.value = UiState.Failure("${response.code()} ${response.message()}")
                    Log.e("error", "${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _signupState.value = UiState.Failure(e.message ?: "${e.message}")
            }
        }
    }
}
