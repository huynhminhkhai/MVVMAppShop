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
                    // Nếu categoryId là 1, lấy tất cả sản phẩm và chọn ngẫu nhiên
                    val allProductsResult = getProducts()
                    if (allProductsResult.isSuccess) {
                        val allProducts = allProductsResult.getOrDefault(emptyList())
                        val randomProducts = allProducts.shuffled().take(10) // Lấy ngẫu nhiên 10 sản phẩm
                        Log.d("ProductsRepository", "Fetched random products: $randomProducts")
                        Result.success(randomProducts)
                    } else {
                        Result.failure(allProductsResult.exceptionOrNull() ?: Exception("Unknown error"))
                    }
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

    // Hàm gọi API để lấy chi tiết sản phẩm dựa trên productId
    suspend fun getProductById(productId: Long): Result<ProductModel> {
        return try {
            val product = RetrofitInstance.productApiService.getProductById(productId)
            Result.success(product)
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching product by ID: ${e.message}")
            Result.failure(e)
        }
    }

}