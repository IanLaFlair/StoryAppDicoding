package com.kls.mystoryapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kls.mystoryapps.model.StoryModel
import com.kls.mystoryapps.model.StoryResponse
import com.kls.mystoryapps.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryViewModel(): ViewModel() {
    val storyList = MutableLiveData<ArrayList<StoryModel>>()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setListStory(token: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getStoryTask("Bearer $token")
        client.enqueue(object: Callback<StoryResponse> {
            override fun onResponse(call: Call<StoryResponse>, response: Response<StoryResponse>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    storyList.value = response.body()?.listStory
                }
            }
            override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }



}