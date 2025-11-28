package com.sopt.dive.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.datasourceImpl.AuthDataSourceImpl
import com.sopt.dive.ui.common.UiState
import com.sopt.dive.data.dto.request.login.RequestLoginDto
import com.sopt.dive.data.model.LoginResponseModel
import com.sopt.dive.data.repository.AuthRepository
import com.sopt.dive.data.repositoryImpl.AuthRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val authRepository: AuthRepository = AuthRepositoryImpl( authDataSource = AuthDataSourceImpl() )

    private val _loginState = MutableStateFlow<UiState<LoginResponseModel?>>(UiState.Loading)
    val loginState: StateFlow<UiState<LoginResponseModel?>> = _loginState.asStateFlow()

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
            flow {
                emit(UiState.Loading)
                val loginInfo = _loginInfo.value
                val request = RequestLoginDto(
                    username = loginInfo.loginId,
                    password = loginInfo.loginPw,
                )
                val result = authRepository.postLogin(request)
                    emit(result.fold(
                        onSuccess = { UiState.Success(it)},
                        onFailure = {UiState.Failure(it.message)}
                    ))
            }.collect {
                _loginState.value = it
            }
        }
    }
}
