package com.sopt.dive.ui.screen.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.datasourceImpl.AuthDataSourceImpl
import com.sopt.dive.data.network.ServicePool
import com.sopt.dive.ui.common.UiState
import com.sopt.dive.data.dto.request.signup.RequestSignUpDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.signup.ResponseSignUpDto
import com.sopt.dive.data.model.SignUpResponseModel
import com.sopt.dive.data.repository.AuthRepository
import com.sopt.dive.data.repositoryImpl.AuthRepositoryImpl
import com.sopt.dive.ui.screen.login.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlin.getValue

class SignUpViewModel: ViewModel() {

    private val authRepository: AuthRepository = AuthRepositoryImpl( authDataSource = AuthDataSourceImpl() )

    private val _signupState = MutableStateFlow<UiState<SignUpResponseModel?>>(UiState.Loading)
    val signupState: StateFlow<UiState<SignUpResponseModel?>> = _signupState.asStateFlow()

    private val _signupInfo = MutableStateFlow(SignUpUiState())
    val signupInfo: StateFlow<SignUpUiState> = _signupInfo.asStateFlow()

    fun onUserUNameChange(userUName: String) {
        val error = if (userUName.isNotBlank()) SignUpValidator.validateId(userUName) ?: "" else ""
        _signupInfo.update { it.copy(userUName = userUName, idError = error) }

    }

    fun onUserPwChange(userPw: String) {
        val error = if (userPw.isNotBlank()) SignUpValidator.validatePw(userPw) ?: "" else ""
        _signupInfo.update { it.copy(userPw = userPw, pwError = error) }
    }

    fun onUserNameChange(userName: String) {
        val error = if (userName.isNotBlank()) SignUpValidator.validateNickname(userName) ?: "" else ""
        _signupInfo.update { it.copy(userName = userName, nickError = error) }
    }

    fun onUserEmailChange(userEmail: String) {
        val error = if (userEmail.isNotBlank()) SignUpValidator.validateEmail(userEmail) ?: "" else ""
        _signupInfo.update { it.copy(userEmail = userEmail, emailError = error) }
    }

    fun onUserAgeChange(userAge: String) {
        val ageInt = userAge.toIntOrNull() ?: 0
        val error = if (userAge.isNotBlank()) SignUpValidator.validateAge(ageInt) ?: "" else ""
        _signupInfo.update { it.copy(userAge = userAge, ageError = error) }
    }

    fun postSignUp() {
        viewModelScope.launch {
            _signupState.emit(UiState.Loading)
            val signupInfo = _signupInfo.value
            val request = RequestSignUpDto(
                username = signupInfo.userUName,
                password = signupInfo.userPw,
                name = signupInfo.userName,
                email = signupInfo.userEmail,
                age = signupInfo.userAge.toIntOrNull() ?: 0
            )
            authRepository.postSignUp(request)
                .onSuccess {
                    _signupState.emit(UiState.Success(it))
                }
                .onFailure {
                    _signupState.emit(UiState.Failure(it.message))
                }

        }
    }
}
