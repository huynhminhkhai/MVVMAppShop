package com.khai.dev.mvvmappshop.repositories

import android.util.Log
import com.khai.dev.mvvmappshop.models.auth.WelcomeModel
import com.khai.dev.mvvmappshop.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WelcomeRepository {
    // Hàm gọi API để lấy dữ liệu welcomes
    suspend fun getWelcomes(): Result<List<WelcomeModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.welcomeApiService.getWelcomes()
                if (response.isNotEmpty()) {
                    Log.d("WelcomeRepository", "Fetched welcomes: $response")  // Log dữ liệu
                    Result.success(response)
                } else {
                    Log.d("WelcomeRepository", "No welcomes found.")
                    Result.success(emptyList())
                }
            } catch (e: Exception) {
                Log.e("WelcomeRepository", "Error fetching welcomes: ${e.message}")
                Result.failure(e)
            }
        }
    }
}