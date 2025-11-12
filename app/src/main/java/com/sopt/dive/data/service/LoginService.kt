package com.sopt.dive.data.service

import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.login.ResponseLoginDto
import com.sopt.dive.data.dto.request.login.RequestLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/auth/login")
    fun postLogin(
        @Body requestLoginDto: RequestLoginDto
    ): Call<BaseResponse<ResponseLoginDto>>
}