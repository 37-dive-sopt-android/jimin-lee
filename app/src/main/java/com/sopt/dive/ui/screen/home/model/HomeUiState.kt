package com.sopt.dive.ui.screen.home.model

import com.sopt.dive.ui.screen.home.type.HomeListType

data class HomeUiState(
    val img: Int,
    val name: String,
    val message: String?,
    val birth: HomeListType.Birth,
    val content: HomeListType.Content,
    val etc: HomeListType.Etc
)