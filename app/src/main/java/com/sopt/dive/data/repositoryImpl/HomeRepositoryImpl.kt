package com.sopt.dive.data.repositoryImpl

import com.sopt.dive.data.datasource.HomeDataSource
import com.sopt.dive.data.model.HomeResponseModel
import com.sopt.dive.data.model.toModel
import com.sopt.dive.data.repository.HomeRepository

class HomeRepositoryImpl(
    private val homeDataSource: HomeDataSource,
) : HomeRepository {
    override suspend fun getHomeList(page: Int, perPage: Int): Result<HomeResponseModel> =
        runCatching {
            homeDataSource.getHomeList(page, perPage).body()?.toModel() ?: throw IllegalArgumentException()
        }
}
