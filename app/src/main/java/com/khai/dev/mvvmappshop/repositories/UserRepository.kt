package com.khai.dev.mvvmappshop.repositories

import android.util.Log
import com.khai.dev.mvvmappshop.models.auth.UserLoginModel
import com.khai.dev.mvvmappshop.models.auth.UserModel
import com.khai.dev.mvvmappshop.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserRepository {
    //Đăng kí
    suspend fun registerUser(userModel: UserModel): Result<UserModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.userApiService.register(userModel)
                if (response.isSuccessful) {
                    Log.d("AuthRepository", "User registered: ${response.body()}")
                    Result.success(response.body()!!)
                } else {
                    Log.e("AuthRepository", "Registration error: ${response.errorBody()?.string()}")
                    Result.failure(Exception(response.errorBody()?.string() ?: "Unknown error"))
                }
            } catch (e: Exception) {
                Log.e("AuthRepository", "Error registering user: ${e.message}")
                Result.failure(e)
            }
        }
    }
    //Đăng nhập
    suspend fun loginUser(userLoginModel: UserLoginModel): Result<UserLoginModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.userApiService.login(userLoginModel)
                if (response.isSuccessful) {
                    Log.d("UserRepository", "User logged in successfully, token: ${response.body()}")
                    Result.success(response.body()!!)
                } else {
                    Log.e("UserRepository", "Login error: ${response.errorBody()?.string()}")
                    Result.failure(Exception(response.errorBody()?.string() ?: "Unknown error"))
                }
            } catch (e: Exception) {
                Log.e("UserRepository", "Error logging in user: ${e.message}")
                Result.failure(e)
            }
        }
    }
}