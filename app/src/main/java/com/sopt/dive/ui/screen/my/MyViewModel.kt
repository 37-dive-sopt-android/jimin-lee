package com.sopt.dive.ui.screen.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.datasourceImpl.AuthDataSourceImpl
import com.sopt.dive.ui.common.UiState
import com.sopt.dive.data.model.UserResponseModel
import com.sopt.dive.data.repository.AuthRepository
import com.sopt.dive.data.repositoryImpl.AuthRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MyViewModel: ViewModel() {

    private val authRepository: AuthRepository = AuthRepositoryImpl( authDataSource = AuthDataSourceImpl() )

    private val _myState = MutableStateFlow<UiState<UserResponseModel?>>(UiState.Loading)
    val myState: StateFlow<UiState<UserResponseModel?>> = _myState.asStateFlow()

    fun getUserData(id: Long) {
        viewModelScope.launch {
            _myState.emit(UiState.Loading)
            authRepository.getUserData(id)
                .onSuccess {
                    _myState.emit(UiState.Success(it))
                }
                .onFailure {
                    _myState.emit(UiState.Failure(it.message))
                }
        }
    }
}
