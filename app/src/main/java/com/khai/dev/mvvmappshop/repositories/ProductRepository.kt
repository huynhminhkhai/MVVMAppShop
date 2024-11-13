package com.khai.dev.mvvmappshop.repositories

import android.util.Log
import com.khai.dev.mvvmappshop.models.home.CategoryModel
import com.khai.dev.mvvmappshop.models.home.ProductModel
import com.khai.dev.mvvmappshop.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository {
    // Hàm gọi API để lấy dữ liệu products
    suspend fun getProducts(): Result<List<ProductModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.productApiService.getProducts()
                if (response.isNotEmpty()) {
                    Log.d("ProductsRepository", "Fetched product: $response")  // Log dữ liệu
                    Result.success(response)
                } else {
                    Log.d("ProductsRepository", "No product found.")
                    Result.success(emptyList())
                }
            } catch (e: Exception) {
                Log.e("ProductsRepository", "Error fetching product: ${e.message}")
                Result.failure(e)
            }
        }
    }

    // Hàm gọi API để lấy sản phẩm theo categoryId
    suspend fun getProductsByCategoryId(categoryId: Long): Result<List<ProductModel>> {
        return withContext(Dispatchers.IO) {
            try {
                if (categoryId == 1L) {
                    // Nếu categoryId là 1, lấy toàn bộ sản phẩm
                    return@withContext getProducts()
                } else {
                    // Gọi API với `categoryId` cụ thể
                    val response = RetrofitInstance.productApiService.getProductsByCategoryId(categoryId)
                    if (response.isNotEmpty()) {
                        Log.d("ProductsRepository", "Fetched products for category $categoryId: $response")
                        Result.success(response)
                    } else {
                        Log.d("ProductsRepository", "No products found for category $categoryId.")
                        Result.success(emptyList())
                    }
                }
            } catch (e: Exception) {
                Log.e("ProductsRepository", "Error fetching products for category $categoryId: ${e.message}")
                Result.failure(e)
            }
        }
    }

}