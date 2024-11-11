package com.khai.dev.mvvmappshop.network

import com.khai.dev.mvvmappshop.models.home.CategoryModel
import com.khai.dev.mvvmappshop.models.home.ProductModel
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductModel>
}