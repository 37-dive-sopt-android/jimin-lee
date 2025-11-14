package com.sopt.dive.data.service

import com.sopt.dive.data.dto.response.home.ResponseHomeListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface HomeService {
    @Headers("x-api-key: reqres-free-v1")
    @GET("/api/users")
    suspend fun getHomeList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ResponseHomeListDto>
}