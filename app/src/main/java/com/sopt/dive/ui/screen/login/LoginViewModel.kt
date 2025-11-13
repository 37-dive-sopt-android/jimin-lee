package com.sopt.dive.ui.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.getValue
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.dto.request.login.RequestLoginDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.login.ResponseLoginDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel: ViewModel() {

    private val loginService by lazy { ServicePool.loginService }

    private val _loginState = MutableStateFlow<BaseResponse<ResponseLoginDto>?>(null)
    val loginState: StateFlow<BaseResponse<ResponseLoginDto>?> = _loginState.asStateFlow()

    fun postLogin(request: RequestLoginDto) {
        loginService.postLogin(request).enqueue(
            object : Callback<BaseResponse<ResponseLoginDto>> {
                override fun onResponse(
                    call: Call<BaseResponse<ResponseLoginDto>>,
                    response: Response<BaseResponse<ResponseLoginDto>>
                ) {
                    if (response.isSuccessful) {
                        _loginState.value = response.body()
                    } else {
                        val errorCode = response.code().toString()
                        val errorMessage = response.message()
                        _loginState.value = BaseResponse(false, errorCode, errorMessage, null)
                        Log.e("error", errorMessage.toString())
                    }
                }
                override fun onFailure(call: Call<BaseResponse<ResponseLoginDto>?>, t: Throwable) {
                    Log.e("failure", t.message.toString())
                }
            }
        )
    }

    fun resetLogin() {
        _loginState.value = null
    }

}
