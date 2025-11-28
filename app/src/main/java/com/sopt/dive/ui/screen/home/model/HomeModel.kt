package com.sopt.dive.ui.screen.home.model

import com.sopt.dive.ui.screen.home.type.HomeListType

data class HomeModel(
    val id: Int,
    val img: String,
    val name: String,
    val message: String?,
    val birth: HomeListType.Birth = HomeListType.Birth.NONE,
    val content: HomeListType.Content = HomeListType.Content.NONE,
    val etc: HomeListType.Etc = HomeListType.Etc.None
)