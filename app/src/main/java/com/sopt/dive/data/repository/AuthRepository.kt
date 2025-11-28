package com.sopt.dive.data.repository

import com.sopt.dive.data.dto.request.login.RequestLoginDto
import com.sopt.dive.data.dto.request.signup.RequestSignUpDto
import com.sopt.dive.data.model.LoginResponseModel
import com.sopt.dive.data.model.SignUpResponseModel
import com.sopt.dive.data.model.UserResponseModel

interface AuthRepository {

    suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): Result<SignUpResponseModel>

    suspend fun postLogin(requestLoginDto: RequestLoginDto): Result<LoginResponseModel>

    suspend fun getUserData(id: Long): Result<UserResponseModel>

}
