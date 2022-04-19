package com.kls.mystoryapps.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kls.mystoryapps.R
import com.kls.mystoryapps.adapter.StoryAdapter
import com.kls.mystoryapps.databinding.ActivityListStoryBinding
import com.kls.mystoryapps.model.StoryModel
import com.kls.mystoryapps.utils.TokenPreference
import com.kls.mystoryapps.viewmodel.StoryViewModel
import com.kls.mystoryapps.viewmodel.TokenViewModel
import com.kls.mystoryapps.viewmodel.ViewModelFactory

class ListStoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListStoryBinding
    private lateinit var tokenViewModel: TokenViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userToken")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListStoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        val pref = TokenPreference.getInstance(dataStore)
        tokenViewModel = ViewModelProvider(this, ViewModelFactory(pref))[TokenViewModel::class.java]

        val storyViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[StoryViewModel::class.java]
        tokenViewModel.getTokens().observe(this
        ) { token: String? ->
            if (token != null){
                storyViewModel.setListStory(token)

            }
        }
        storyViewModel.storyList.observe(this) { storyList ->
            getStoryList(storyList)
        }
        storyViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuTambah -> {
                val intent = Intent(this,AddStoryActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menuLogout -> {
                tokenViewModel.removeTokens()
                val intent = Intent(this,AuthActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
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