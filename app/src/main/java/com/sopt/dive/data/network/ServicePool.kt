package com.sopt.dive.data.network

import com.sopt.dive.data.service.AuthService
import kotlin.getValue
import com.sopt.dive.data.service.HomeService

object ServicePool {
    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }

    val homeService: HomeService by lazy {
        ApiFactory.create2<HomeService>()
    }
}
