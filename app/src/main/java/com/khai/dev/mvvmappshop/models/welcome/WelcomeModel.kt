package com.khai.dev.mvvmappshop.models.welcome

import com.google.gson.annotations.SerializedName
import com.khai.dev.mvvmappshop.utils.BaseApiConfig

data class WelcomeModel(
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("imageUrl")
    val imageUrl: String
){
    val fullImageUrl: String
        get() = BaseApiConfig.BASE_URL+"welcomes" + imageUrl
}
