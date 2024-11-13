package com.khai.dev.mvvmappshop.network

import com.khai.dev.mvvmappshop.models.auth.UserLoginModel
import com.khai.dev.mvvmappshop.models.auth.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface UserApiService {
    @POST("users/register")
    suspend fun register(@Body userModel: UserModel): Response<UserModel>
    @POST("users/login")
    suspend fun login(@Body userLoginModel: UserLoginModel): Response<UserLoginModel> // Giả sử server trả về token dưới dạng String
}