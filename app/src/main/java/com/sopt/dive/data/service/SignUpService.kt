package com.sopt.dive.data.service

import com.sopt.dive.data.dto.BaseResponse
import com.sopt.dive.data.dto.signup.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("/api/v1/users")
    fun postSignUp(
        @Body responseSignUpDto: ResponseSignUpDto
    ): Call<BaseResponse<ResponseSignUpDto>>
}