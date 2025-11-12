package com.sopt.dive.data.service

import com.sopt.dive.data.dto.request.signup.RequestSignUpDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.signup.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("/api/v1/users")
    fun postSignUp(
        @Body requestSignUpDto: RequestSignUpDto
    ): Call<BaseResponse<ResponseSignUpDto>>
}