package com.kls.mystoryapps.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kls.mystoryapps.adapter.StoryAdapter
import com.kls.mystoryapps.databinding.ActivityListStoryBinding
import com.kls.mystoryapps.databinding.ActivityLoginBinding
import com.kls.mystoryapps.model.StoryModel
import com.kls.mystoryapps.utils.TokenPreference
import com.kls.mystoryapps.viewmodel.StoryViewModel
import com.kls.mystoryapps.viewmodel.TokenViewModel
import com.kls.mystoryapps.viewmodel.ViewModelFactory

class ListStoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListStoryBinding
//    private var storyViewModel: StoryViewModel = StoryViewModel(null)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListStoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val token = intent.getStringExtra("tokenExtra")
        val storyViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(StoryViewModel::class.java)
        if (token != null) {
            storyViewModel.setListStory(token)
        }
        storyViewModel.storyList.observe(this) { storyList ->
            getStoryList(storyList)
        }
        storyViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun getStoryList(listStory: ArrayList<StoryModel>) {

        val adapter = StoryAdapter(listStory)
        binding.rvListStory.adapter = adapter
        binding.rvListStory.layoutManager = GridLayoutManager(this,2)

    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbStory.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}