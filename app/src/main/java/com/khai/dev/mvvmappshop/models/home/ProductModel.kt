package com.khai.dev.mvvmappshop.models.home

import com.google.gson.annotations.SerializedName
import com.khai.dev.mvvmappshop.utils.BaseApiConfig

data class ProductModel(
    val id: Long,
    val name: String,
    val price: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    val description: String,
    @SerializedName("category_id")
    val categoryId: String
){
    val fullImageUrl: String
        get() = BaseApiConfig.BASE_URL+"products" + imageUrl
}