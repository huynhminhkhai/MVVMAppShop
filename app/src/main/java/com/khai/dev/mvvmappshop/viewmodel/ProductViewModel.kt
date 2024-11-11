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
}