package com.khai.dev.mvvmappshop.models.home

import com.google.gson.annotations.SerializedName
import com.khai.dev.mvvmappshop.utils.BaseApiConfig

data class CategoryModel(
    val id: Long,
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String
){
    val fullImageUrl: String
        get() = BaseApiConfig.BASE_URL+"categories" + imageUrl
}