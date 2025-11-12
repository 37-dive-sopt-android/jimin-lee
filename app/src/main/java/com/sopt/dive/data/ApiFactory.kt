package com.sopt.dive.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.dive.BuildConfig
import com.sopt.dive.data.service.LoginService
import com.sopt.dive.data.service.MyService
import com.sopt.dive.data.service.SignUpService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import kotlin.getValue

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.BASE_URL

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("/".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}

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
}