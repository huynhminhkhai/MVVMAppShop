package com.khai.dev.mvvmappshop.network

import com.khai.dev.mvvmappshop.models.home.CategoryModel
import com.khai.dev.mvvmappshop.models.home.ProductModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductModel>

    @GET("products/byCategory")
    suspend fun getProductsByCategoryId(
        @Query("categoryId") categoryId: Long
    ): List<ProductModel>
}