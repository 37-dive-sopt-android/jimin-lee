package com.sopt.dive.data.service

import com.sopt.dive.data.dto.login.ResponseLoginDto
import com.sopt.dive.data.dto.my.ResponseUserDataDto
import com.sopt.dive.data.dto.request.login.RequestLoginDto
import com.sopt.dive.data.dto.request.signup.RequestSignUpDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.signup.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    @POST("users")
    suspend fun postSignUp(
        @Body requestSignUpDto: RequestSignUpDto
    ): BaseResponse<ResponseSignUpDto>

    @POST("auth/login")
    suspend fun postLogin(
        @Body requestLoginDto: RequestLoginDto
    ): BaseResponse<ResponseLoginDto>

    @GET("users/{id}")
    suspend fun getUserData(
        @Path("id") id: Long
    ): BaseResponse<ResponseUserDataDto>

}