package com.sopt.dive.data.model

import com.sopt.dive.data.dto.response.home.ResponseHomeDataDto
import com.sopt.dive.data.dto.response.home.ResponseHomeListDto
import com.sopt.dive.ui.screen.home.model.HomeModel
import com.sopt.dive.ui.screen.home.type.HomeListType

data class HomeResponseModel(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<HomeModel>
)

fun ResponseHomeListDto.toModel() =
    HomeResponseModel(
        page = this.page,
        perPage = this.perPage,
        total = this.total,
        totalPages = this.totalPages,
        data = this.data.map { it.toModel() },
    )

fun ResponseHomeDataDto.toModel() =
    HomeModel(
        id = this.id,
        img = this.avatar,
        name = "${this.firstName} ${this.lastName}",
        message = this.email,
        birth = HomeListType.Birth.NONE,
        content = HomeListType.Content.NONE,
        etc = HomeListType.Etc.None
    )