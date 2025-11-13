package com.sopt.dive.ui.screen.my

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.getValue
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.dto.request.login.RequestLoginDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.my.ResponseUserDataDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyViewModel: ViewModel() {

    private val myService by lazy { ServicePool.myService }

    private val _myState = MutableStateFlow<BaseResponse<ResponseUserDataDto>?>(null)
    val myState: StateFlow<BaseResponse<ResponseUserDataDto>?> = _myState.asStateFlow()

    fun getUserData(id: Long) {
        myService.getUserData(id).enqueue(
            object : Callback<BaseResponse<ResponseUserDataDto>> {
                override fun onResponse(
                    call: Call<BaseResponse<ResponseUserDataDto>>,
                    response: Response<BaseResponse<ResponseUserDataDto>>
                ) {
                    if (response.isSuccessful) {
                        _myState.value = response.body()
                        Log.d("login_my","${id}")
                    } else {
                        val errorCode = response.code().toString()
                        val errorMessage = response.message()
                        _myState.value = BaseResponse(false, errorCode, errorMessage, null)
                        Log.e("error", errorMessage.toString())
                    }
                }
                override fun onFailure(call: Call<BaseResponse<ResponseUserDataDto>?>, t: Throwable) {
                    Log.e("failure", t.message.toString())
                }
            }
        )
    }

    /*fun resetUserData() {
        _myState.value = null
    }*/

}
