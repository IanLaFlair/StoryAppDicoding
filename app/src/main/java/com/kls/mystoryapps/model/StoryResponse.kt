package com.kls.mystoryapps.model
import com.google.gson.annotations.SerializedName

data class StoryResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("listStory")
    val listStory: ArrayList<StoryModel>,
    @SerializedName("message")
    val message: String
)
