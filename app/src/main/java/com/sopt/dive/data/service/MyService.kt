package com.sopt.dive.data.service

import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.my.ResponseUserDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MyService {
    @GET("/api/v1/users/{id}")
    suspend fun getUserData(
        @Path("id") id: Long
    ): Response<BaseResponse<ResponseUserDataDto>>
}