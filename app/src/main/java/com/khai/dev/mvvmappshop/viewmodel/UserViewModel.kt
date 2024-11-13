package com.khai.dev.mvvmappshop.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khai.dev.mvvmappshop.models.auth.UserLoginModel
import com.khai.dev.mvvmappshop.models.auth.UserModel
import com.khai.dev.mvvmappshop.repositories.UserRepository
import kotlinx.coroutines.launch


class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()

    //Đăng kí
    private val _registerStatus = MutableLiveData<Result<UserModel>>()
    val registerStatus: LiveData<Result<UserModel>> = _registerStatus
    fun register(userModel: UserModel) {
        viewModelScope.launch {
            val result = userRepository.registerUser(userModel)
            if (result.isSuccess) {
                Log.d("AuthViewModel", "User registered successfully")
                _registerStatus.postValue(result)
            } else {
                Log.e("AuthViewModel", "Error registering user: ${result.exceptionOrNull()?.message}")
                _registerStatus.postValue(result)
            }
        }
    }

    //Đăng nhập
    private val _loginStatus = MutableLiveData<Result<UserLoginModel>>()
    val loginStatus: LiveData<Result<UserLoginModel>> = _loginStatus
    fun login(userLoginModel: UserLoginModel) {
        viewModelScope.launch {
            val result = userRepository.loginUser(userLoginModel)
            if (result.isSuccess) {
                Log.d("UserViewModel", "User logged in successfully")
                _loginStatus.postValue(result)
            } else {
                Log.e("UserViewModel", "Error logging in user: ${result.exceptionOrNull()?.message}")
                _loginStatus.postValue(result)
            }
        }
    }
}