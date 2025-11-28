package com.sopt.dive.data.network

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("x-api-key", "reqres-free-v1")
            .build()
        proceed(newRequest)
    }
}