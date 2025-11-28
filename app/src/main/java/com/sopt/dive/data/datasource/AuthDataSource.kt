package com.sopt.dive.data.datasource

import com.sopt.dive.data.dto.login.ResponseLoginDto
import com.sopt.dive.data.dto.my.ResponseUserDataDto
import com.sopt.dive.data.dto.request.login.RequestLoginDto
import com.sopt.dive.data.dto.request.signup.RequestSignUpDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.signup.ResponseSignUpDto

interface AuthDataSource {
    suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): BaseResponse<ResponseSignUpDto>

    suspend fun postLogin(requestLoginDto: RequestLoginDto): BaseResponse<ResponseLoginDto>

    suspend fun getUserData(id: Long): BaseResponse<ResponseUserDataDto>
}
