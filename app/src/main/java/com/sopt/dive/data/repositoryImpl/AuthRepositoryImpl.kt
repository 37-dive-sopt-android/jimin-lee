package com.sopt.dive.data.repositoryImpl

import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.data.dto.login.ResponseLoginDto
import com.sopt.dive.data.dto.my.ResponseUserDataDto
import com.sopt.dive.data.dto.request.login.RequestLoginDto
import com.sopt.dive.data.dto.request.signup.RequestSignUpDto
import com.sopt.dive.data.dto.signup.ResponseSignUpDto
import com.sopt.dive.data.model.LoginResponseModel
import com.sopt.dive.data.model.SignUpResponseModel
import com.sopt.dive.data.model.UserResponseModel
import com.sopt.dive.data.model.toModel
import com.sopt.dive.data.repository.AuthRepository

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
) : AuthRepository {

    override suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): Result<SignUpResponseModel> =
        runCatching {
            authDataSource.postSignUp(requestSignUpDto).data?.toModel() ?: throw IllegalArgumentException()
        }

    override suspend fun postLogin(requestLoginDto: RequestLoginDto): Result<LoginResponseModel> =
        runCatching {
            authDataSource.postLogin(requestLoginDto).data?.toModel() ?: throw IllegalArgumentException()
        }

    override suspend fun getUserData(id: Long): Result<UserResponseModel> =
        runCatching {
            authDataSource.getUserData(id).data?.toModel() ?: throw IllegalArgumentException()
        }
}
