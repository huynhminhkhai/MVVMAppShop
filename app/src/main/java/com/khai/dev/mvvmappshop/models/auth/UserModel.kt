package com.khai.dev.mvvmappshop.models.auth

import com.google.gson.annotations.SerializedName

data class UserModel(
    val name: String,
    val password: String,
    @SerializedName("retype_password")
    val retypePassword: String,
    val email: String
)
data class UserLoginModel(
    val email: String,
    val password: String
)