package com.kls.mystoryapps.utils

import com.kls.mystoryapps.model.LoginResponse
import com.kls.mystoryapps.model.RegisterResponse
import com.kls.mystoryapps.model.StoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @FormUrlEncoded
    @POST("register")
    fun registerUserTask(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun loginUserTask(
        @Field("email") email: String,
        @Field("password") password: String): Call<LoginResponse>

    @GET("stories")
    fun getStoryTask(
        @Header("Authorization") token: String
    ): Call<StoryResponse>

    @Multipart
    @POST("stories")
    fun uploadStoryTask(
        @Header("Authorization") token: String,
        @Part("description") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<RegisterResponse>

}