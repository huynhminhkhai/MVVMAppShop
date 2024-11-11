package com.khai.dev.mvvmappshop.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khai.dev.mvvmappshop.models.auth.WelcomeModel
import com.khai.dev.mvvmappshop.repositories.WelcomeRepository
import kotlinx.coroutines.launch

class WelcomeViewModel : ViewModel() {
    private val _welcomes = MutableLiveData<List<WelcomeModel>>()
    val welcomes: LiveData<List<WelcomeModel>> = _welcomes

    private val welcomeRepository = WelcomeRepository()

    init {
        getWelcomes()
    }

    private fun getWelcomes() {
        viewModelScope.launch {
            val result = welcomeRepository.getWelcomes()

            if (result.isSuccess) {
                Log.d("WelcomeViewModel", "Welcomes successfully fetched.")
                _welcomes.postValue(result.getOrNull() ?: emptyList())
            } else {
                Log.e("WelcomeViewModel", "Error fetching welcomes: ${result.exceptionOrNull()?.message}")
                _welcomes.postValue(emptyList())
            }
        }
    }
}