package com.sopt.dive.data.datasourceImpl

import com.sopt.dive.data.datasource.HomeDataSource
import com.sopt.dive.data.dto.response.home.ResponseHomeListDto
import com.sopt.dive.data.network.ServicePool.homeService
import retrofit2.Response

class HomeDataSourceImpl : HomeDataSource {

    override suspend fun getHomeList(page: Int, perPage: Int): Response<ResponseHomeListDto> =
        homeService.getHomeList(page,perPage)

}
