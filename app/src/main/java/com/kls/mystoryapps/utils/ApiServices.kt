package com.kls.mystoryapps.utils

import com.kls.githubuserian.model.UserDetailResponse
import com.kls.githubuserian.model.UserModel
import com.kls.githubuserian.model.UserResponse
import com.kls.mystoryapps.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("/register")
    fun registerUserTask(@Field("name") name: String,
    @Field("email") email: String,
    @Field("password") password: String): Call<RegisterResponse>



}