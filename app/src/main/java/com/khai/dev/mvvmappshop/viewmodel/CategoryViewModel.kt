package com.khai.dev.mvvmappshop.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khai.dev.mvvmappshop.models.auth.WelcomeModel
import com.khai.dev.mvvmappshop.models.home.CategoryModel
import com.khai.dev.mvvmappshop.repositories.CategoryRepository
import com.khai.dev.mvvmappshop.repositories.WelcomeRepository
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> = _categories

    private val categoryRepository = CategoryRepository()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            val result = categoryRepository.getCategories()

            if (result.isSuccess) {
                Log.d("CategoryViewModel", "Category successfully fetched.")
                _categories.postValue(result.getOrNull() ?: emptyList())
            } else {
                Log.e("CategoryViewModel", "Error fetching Category: ${result.exceptionOrNull()?.message}")
                _categories.postValue(emptyList())
            }
        }
    }
}