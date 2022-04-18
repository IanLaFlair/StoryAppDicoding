package com.kls.mystoryapps.utils

import com.kls.mystoryapps.model.LoginResponse
import com.kls.mystoryapps.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @GET("register")
    fun registerUserTask(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun loginUserTask(
        @Field("email") email: String,
        @Field("password") password: String): Call<LoginResponse>



}