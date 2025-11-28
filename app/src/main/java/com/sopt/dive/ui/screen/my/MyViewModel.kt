package com.sopt.dive.ui.screen.my

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.getValue
import com.sopt.dive.data.network.ServicePool
import com.sopt.dive.ui.common.UiState
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.my.ResponseUserDataDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MyViewModel: ViewModel() {

    private val myService by lazy { ServicePool.authService }

    private val _myState = MutableStateFlow<UiState<BaseResponse<ResponseUserDataDto>?>>(UiState.Loading)
    val myState: StateFlow<UiState<BaseResponse<ResponseUserDataDto>?>> = _myState.asStateFlow()

    fun getUserData(id: Long) {
        viewModelScope.launch {
            _myState.value = UiState.Loading
            try {
                val response = myService.getUserData(id)
                if (response.isSuccessful) {
                    _myState.value = UiState.Success(response.body())
                } else {
                    _myState.value = UiState.Failure("${response.code()} ${response.message()}")
                    Log.e("error", "${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _myState.value = UiState.Failure(e.message ?: "${e.message}")
            }
        }
    }
}
