package com.sopt.dive.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.ServicePool2
import com.sopt.dive.data.UiState
import com.sopt.dive.data.dto.response.home.ResponseHomeListDto
import com.sopt.dive.ui.screen.home.model.HomeUiState
import com.sopt.dive.ui.screen.home.type.HomeListType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.getValue

class HomeViewModel : ViewModel() {

    private val homeService by lazy { ServicePool2.homeService }

    private val _homeState = MutableStateFlow<UiState<List<HomeUiState>>>(UiState.Loading)
    val homeState: StateFlow<UiState<List<HomeUiState>>> = _homeState.asStateFlow()

    init {
        getHomeList()
    }

    fun getHomeList() {
        viewModelScope.launch {
            _homeState.value = UiState.Loading
            try {
                val response = homeService.getHomeList(1,30)
                if (response.isSuccessful) {
                    val followerList =
                        response.body()?.data?.map { item ->
                        HomeUiState(
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
        }
    }
}