package com.khai.dev.mvvmappshop.network

import com.khai.dev.mvvmappshop.models.auth.WelcomeModel
import com.khai.dev.mvvmappshop.models.home.CategoryModel
import retrofit2.http.GET

interface CategoryApiService {
    @GET("categories")
    suspend fun getCategories(): List<CategoryModel>
}