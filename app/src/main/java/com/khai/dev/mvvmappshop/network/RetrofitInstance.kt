package com.khai.dev.mvvmappshop.network

// api/RetrofitInstance.kt
import com.khai.dev.mvvmappshop.utils.BaseApiConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
//    private const val BASE_URL = "http://172.16.106.205:1402/api/" // Thay URL của API của bạn ở đây

    // Khởi tạo Retrofit một lần duy nhất
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BaseApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Sử dụng Gson để parse JSON
            .build()
    }

    // API service cho Welcome
    val welcomeApiService: WelcomeApiService by lazy {
        retrofit.create(WelcomeApiService::class.java)
    }
}
