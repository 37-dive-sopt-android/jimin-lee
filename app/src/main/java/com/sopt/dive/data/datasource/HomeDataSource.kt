package com.sopt.dive.data.datasource

import com.sopt.dive.data.dto.response.home.ResponseHomeListDto
import retrofit2.Response

interface HomeDataSource {

    suspend fun getHomeList(page: Int, perPage: Int): Response<ResponseHomeListDto>

}
