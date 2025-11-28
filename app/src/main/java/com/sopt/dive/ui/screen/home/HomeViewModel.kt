package com.sopt.dive.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.datasourceImpl.AuthDataSourceImpl
import com.sopt.dive.data.datasourceImpl.HomeDataSourceImpl
import com.sopt.dive.data.network.ServicePool
import com.sopt.dive.ui.common.UiState
import com.sopt.dive.data.dto.response.home.ResponseHomeListDto
import com.sopt.dive.data.model.HomeResponseModel
import com.sopt.dive.data.repository.AuthRepository
import com.sopt.dive.data.repository.HomeRepository
import com.sopt.dive.data.repositoryImpl.HomeRepositoryImpl
import com.sopt.dive.ui.screen.home.model.HomeModel
import com.sopt.dive.ui.screen.home.type.HomeListType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.getValue

class HomeViewModel : ViewModel() {

    private val homeRepository: HomeRepository = HomeRepositoryImpl( homeDataSource = HomeDataSourceImpl())

    private val _homeState = MutableStateFlow<UiState<HomeResponseModel>>(UiState.Loading)
    val homeState: StateFlow<UiState<HomeResponseModel>> = _homeState.asStateFlow()

    init {
        getHomeList()
    }

    fun getHomeList() {
        viewModelScope.launch {
            _homeState.emit(UiState.Loading)
            homeRepository.getHomeList(1, 30)
                .onSuccess {
                    _homeState.emit(UiState.Success(it))
                }
                .onFailure { e ->
                    _homeState.emit(UiState.Failure(e.message))
                }
        }
            /*try {
                val response = homeService.getHomeList(1,30)
                if (response.isSuccessful) {
                    val followerList =
                        response.body()?.data?.map { item ->
                            HomeModel(
                                id = item.id,
                                img = item.avatar,
                                name = "${item.firstName} ${item.lastName}",
                                message = item.email,
                                birth = HomeListType.Birth.NONE,
                                content = HomeListType.Content.Exist,
                                etc = HomeListType.Etc.None
                        )
                    } ?: emptyList()
                    _homeState.value = UiState.Success(followerList)
                } else {
                    _homeState.value = UiState.Failure("${response.code()} ${response.message()}")
                    Log.e("error", "${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _homeState.value = UiState.Failure(e.message ?: "${e.message}")
            }
        }*/
    }
}