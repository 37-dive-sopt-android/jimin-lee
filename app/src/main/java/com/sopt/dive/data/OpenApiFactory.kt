package com.sopt.dive.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.dive.BuildConfig
import com.sopt.dive.data.service.HomeService
import com.sopt.dive.data.service.LoginService
import com.sopt.dive.data.service.MyService
import com.sopt.dive.data.service.SignUpService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import kotlin.getValue

object OpenApiFactory {
    private const val BASE_URL2: String = BuildConfig.BASE_URL2

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client2 = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit2: Retrofit by lazy {
        val jsonConfig = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .client(client2)
            .addConverterFactory(jsonConfig.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit2.create(T::class.java)
}

object ServicePool2 {
    val homeService: HomeService by lazy {
        OpenApiFactory.create<HomeService>()
    }
}