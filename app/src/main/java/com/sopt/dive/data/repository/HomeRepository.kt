package com.sopt.dive.data.repository

import com.sopt.dive.data.model.HomeResponseModel

interface HomeRepository {

    suspend fun getHomeList(page: Int, perPage: Int): Result<HomeResponseModel>

}
