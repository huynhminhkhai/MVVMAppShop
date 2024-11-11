package com.khai.dev.mvvmappshop.repositories

import android.util.Log
import com.khai.dev.mvvmappshop.models.auth.WelcomeModel
import com.khai.dev.mvvmappshop.models.home.CategoryModel
import com.khai.dev.mvvmappshop.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository {
    // Hàm gọi API để lấy dữ liệu welcomes
    suspend fun getCategories(): Result<List<CategoryModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.categoryApiService.getCategories()
                if (response.isNotEmpty()) {
                    Log.d("CategoryRepository", "Fetched category: $response")  // Log dữ liệu
                    Result.success(response)
                } else {
                    Log.d("CategoryRepository", "No category found.")
                    Result.success(emptyList())
                }
            } catch (e: Exception) {
                Log.e("CategoryRepository", "Error fetching category: ${e.message}")
                Result.failure(e)
            }
        }
    }
}