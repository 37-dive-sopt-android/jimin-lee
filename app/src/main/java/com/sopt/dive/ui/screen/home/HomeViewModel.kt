package com.sopt.dive.ui.screen.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sopt.dive.R
import com.sopt.dive.data.ServicePool2
import com.sopt.dive.data.dto.my.ResponseUserDataDto
import com.sopt.dive.data.dto.response.home.ResponseHomeListDto
import com.sopt.dive.ui.screen.home.model.HomeUiState
import com.sopt.dive.ui.screen.home.type.HomeListType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.getValue

class HomeViewModel : ViewModel() {

    private val homeService by lazy { ServicePool2.homeService }

    private val _homeState = MutableStateFlow<List<HomeUiState>>(emptyList())
    val homeState: StateFlow<List<HomeUiState>> = _homeState.asStateFlow()

    init {
        getHomeList()
    }

    fun getHomeList() {
        homeService.getHomeList(1,30).enqueue(
            object : Callback<ResponseHomeListDto> {
                override fun onResponse(
                    call: Call<ResponseHomeListDto>,
                    response: Response<ResponseHomeListDto>
                ) {
                    if (response.isSuccessful) {
                        val followerList = response.body()?.data
                        _homeState.value = followerList?.map{ item ->
                            HomeUiState(
                                img = item.avatar,
                                name = "${item.firstName} ${item.lastName}",
                                message = item.email,
                                birth = HomeListType.Birth.NONE,
                                content = HomeListType.Content.Exist,
                                etc = HomeListType.Etc.None
                            )
                        } ?: emptyList()
                        //Log.d("home","${it.data?}")
                    } else {
                        val error = response.message()
                        Log.e("error", error.toString())
                    }
                }
                override fun onFailure(call: Call<ResponseHomeListDto>, t: Throwable) {
                    Log.e("failure", t.message.toString())
                }
            }
        )

        /*_homeUiState.value = listOf(
            HomeUiState(
                img = R.drawable.profile,
                name = "이지민",
                message = "안녕",
                birth = HomeListType.Birth.NONE,
                content = HomeListType.Content.Exist,
                etc = HomeListType.Etc.Music("Blue Valentine - NMIXX")
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "동동일",
                message = "너무 춥다",
                birth = HomeListType.Birth.NONE,
                content = HomeListType.Content.Exist,
                etc = HomeListType.Etc.None
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "동동이",
                message = "동글동글",
                birth = HomeListType.Birth.BIRTH,
                content = HomeListType.Content.Exist,
                etc = HomeListType.Etc.Gift
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "동동삼",
                message = "졸리다",
                birth = HomeListType.Birth.NONE,
                content = HomeListType.Content.Exist,
                etc = HomeListType.Etc.None
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "동동사",
                message = null,
                birth = HomeListType.Birth.NONE,
                content = HomeListType.Content.NONE,
                etc = HomeListType.Etc.None
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "동동오",
                message = "안녕",
                birth = HomeListType.Birth.NONE,
                content = HomeListType.Content.Exist,
                etc = HomeListType.Etc.Music("Man in a Movie - DAY6")
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "동동육",
                message = "벌써 11월이라니이이이",
                birth = HomeListType.Birth.NONE,
                content = HomeListType.Content.Exist,
                etc = HomeListType.Etc.Music("number one rockstar - 다영(DAYOUNG)")
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "동동칠",
                message = null,
                birth = HomeListType.Birth.BIRTH,
                content = HomeListType.Content.NONE,
                etc = HomeListType.Etc.Gift
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "동동팔",
                message = null,
                birth = HomeListType.Birth.NONE,
                content = HomeListType.Content.NONE,
                etc = HomeListType.Etc.None
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "동동구",
                message = "나는 동동구",
                birth = HomeListType.Birth.NONE,
                content = HomeListType.Content.Exist,
                etc = HomeListType.Etc.None
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "일동동",
                message = null,
                birth = HomeListType.Birth.NONE,
                content = HomeListType.Content.NONE,
                etc = HomeListType.Etc.Music("Shopper - 아이유")
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "이동동",
                message = "과자 먹고 싶다",
                birth = HomeListType.Birth.NONE,
                content = HomeListType.Content.Exist,
                etc = HomeListType.Etc.Music("눈이 오잖아 (feat. 헤이즈) - 이무진")
            ),
            HomeUiState(
                img = R.drawable.profile,
                name = "삼동동",
                message = "끝!",
                birth = HomeListType.Birth.NONE,
                content = HomeListType.Content.Exist,
                etc = HomeListType.Etc.None
            )
        )*/
    }
}