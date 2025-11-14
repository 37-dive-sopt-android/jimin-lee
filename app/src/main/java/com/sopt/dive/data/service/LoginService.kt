package com.sopt.dive.data.service

import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.login.ResponseLoginDto
import com.sopt.dive.data.dto.request.login.RequestLoginDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/api/v1/auth/login")
    suspend fun postLogin(
        @Body requestLoginDto: RequestLoginDto
    ): Response<BaseResponse<ResponseLoginDto>>
}