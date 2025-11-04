package com.sopt.dive.data

import com.sopt.dive.data.type.HomeListType

data class Home(
    val img: Int,
    val name: String,
    val message: String,
    val birth: HomeListType.Birth,
    val content: HomeListType.Content,
    val etc: HomeListType.Etc
)