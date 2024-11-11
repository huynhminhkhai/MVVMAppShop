package com.khai.dev.mvvmappshop.network

import com.khai.dev.mvvmappshop.models.auth.WelcomeModel
import retrofit2.http.GET

interface WelcomeApiService {
    @GET("welcomes")
    suspend fun getWelcomes(): List<WelcomeModel>
}