package com.sopt.dive.data.network

import kotlin.getValue
import com.sopt.dive.data.service.LoginService
import com.sopt.dive.data.service.MyService
import com.sopt.dive.data.service.SignUpService
import com.sopt.dive.data.service.HomeService

object ServicePool {
    val signupService: SignUpService by lazy {
        ApiFactory.create<SignUpService>()
    }
    val loginService: LoginService by lazy {
        ApiFactory.create<LoginService>()
    }
    val myService: MyService by lazy {
        ApiFactory.create<MyService>()
    }

    val homeService: HomeService by lazy {
        ApiFactory.create2<HomeService>()
    }
}
