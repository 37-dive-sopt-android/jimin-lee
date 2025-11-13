package com.sopt.dive.ui.screen.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.dto.request.signup.RequestSignUpDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.signup.ResponseSignUpDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.getValue

class SignUpViewModel: ViewModel() {

    private val signupService by lazy { ServicePool.signupService }

    private val _signupState = MutableStateFlow<BaseResponse<ResponseSignUpDto>?>(null)
    val signupState: StateFlow<BaseResponse<ResponseSignUpDto>?> = _signupState.asStateFlow()

    fun postSignUp(request: RequestSignUpDto) {
        signupService.postSignUp(request).enqueue(
            object : Callback<BaseResponse<ResponseSignUpDto>> {
                override fun onResponse(
                    call: Call<BaseResponse<ResponseSignUpDto>>,
                    response: Response<BaseResponse<ResponseSignUpDto>>
                ) {
                    if (response.isSuccessful) {
                        _signupState.value = response.body()
                    } else {
                        val errorCode = response.code().toString()
                        val errorMessage = response.message()
                        _signupState.value = BaseResponse(false, errorCode, errorMessage, null)
                        Log.e("error", errorMessage.toString())
                    }
                }
                override fun onFailure(call: Call<BaseResponse<ResponseSignUpDto>?>, t: Throwable) {
                    Log.e("failure", t.message.toString())
                }
            }
        )
    }

    fun resetSignUp() {
        _signupState.value = null
    }
}
