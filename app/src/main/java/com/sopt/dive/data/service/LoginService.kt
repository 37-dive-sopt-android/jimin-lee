package com.sopt.dive.data.service

import com.sopt.dive.data.dto.BaseResponse
import com.sopt.dive.data.dto.login.ResponseLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/auth/login")
    fun postLogin(
        @Body responseLoginDto: ResponseLoginDto
    ): Call<BaseResponse<ResponseLoginDto>>
}