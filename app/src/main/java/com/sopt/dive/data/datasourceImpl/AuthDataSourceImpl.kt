package com.sopt.dive.data.datasourceImpl

import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.data.dto.login.ResponseLoginDto
import com.sopt.dive.data.dto.my.ResponseUserDataDto
import com.sopt.dive.data.dto.request.login.RequestLoginDto
import com.sopt.dive.data.dto.request.signup.RequestSignUpDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.signup.ResponseSignUpDto
import com.sopt.dive.data.network.ServicePool.authService

class AuthDataSourceImpl : AuthDataSource {

    override suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): BaseResponse<ResponseSignUpDto> =
        authService.postSignUp(requestSignUpDto)

    override suspend fun postLogin(requestLoginDto: RequestLoginDto): BaseResponse<ResponseLoginDto> =
        authService.postLogin(requestLoginDto)

    override suspend fun getUserData(id: Long): BaseResponse<ResponseUserDataDto> =
        authService.getUserData(id)

}
