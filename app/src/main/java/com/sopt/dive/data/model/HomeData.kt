package com.sopt.dive.data.model

import com.sopt.dive.ui.screen.home.type.HomeListType

data class Home(
    val img: Int,
    val name: String,
    val message: String,
    val birth: HomeListType.Birth,
    val content: HomeListType.Content,
    val etc: HomeListType.Etc
)