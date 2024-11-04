package com.khai.dev.mvvmappshop.services

import com.khai.dev.mvvmappshop.models.welcome.WelcomeModel
import retrofit2.http.GET

interface WelcomeApiService {
    @GET("welcomes")
    suspend fun getWelcomes(): List<WelcomeModel>
}