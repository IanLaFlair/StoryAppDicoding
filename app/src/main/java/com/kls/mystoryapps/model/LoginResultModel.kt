package com.kls.mystoryapps.model
import com.google.gson.annotations.SerializedName


data class LoginResultModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("userId")
    val userId: String
)