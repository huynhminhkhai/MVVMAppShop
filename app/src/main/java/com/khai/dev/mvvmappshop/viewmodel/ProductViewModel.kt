package com.khai.dev.mvvmappshop.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khai.dev.mvvmappshop.models.home.CategoryModel
import com.khai.dev.mvvmappshop.models.home.ProductModel
import com.khai.dev.mvvmappshop.repositories.CategoryRepository
import com.khai.dev.mvvmappshop.repositories.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> = _products

    private val productRepository = ProductRepository()

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            val result = productRepository.getProducts()

            if (result.isSuccess) {
                Log.d("ProductViewModel", "Products successfully fetched.")
                _products.postValue(result.getOrNull() ?: emptyList())
            } else {
                Log.e("ProductViewModel", "Error fetching Product: ${result.exceptionOrNull()?.message}")
                _products.postValue(emptyList())
            }
        }
    }

    // Hàm lấy sản phẩm theo categoryId
    fun getProductsByCategoryId(categoryId: Long) {
        viewModelScope.launch {
            val result = productRepository.getProductsByCategoryId(categoryId)
            if (result.isSuccess) {
                _products.postValue(result.getOrNull() ?: emptyList())
            } else {
                _products.postValue(emptyList())
            }
        }
    }

    // Hàm lấy chi tiết sản phẩm theo productId
    private val _productDetail = MutableLiveData<ProductModel?>()
    val productDetail: LiveData<ProductModel?> = _productDetail

    fun getProductById(productId: Long) {
        viewModelScope.launch {
            val result = productRepository.getProductById(productId)
            if (result.isSuccess) {
                _productDetail.postValue(result.getOrNull())
            } else {
                _productDetail.postValue(null)
                Log.e("ProductViewModel", "Error fetching Product by ID: ${result.exceptionOrNull()?.message}")
            }
        }
    }

}