package com.kls.mystoryapps.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class StoryResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("listStory")
    val listStory: ArrayList<StoryModel>,
    @SerializedName("message")
    val message: String
)
