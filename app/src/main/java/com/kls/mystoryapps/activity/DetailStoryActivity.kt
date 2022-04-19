package com.kls.mystoryapps.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kls.mystoryapps.databinding.ActivityDetailStoryBinding
import com.kls.mystoryapps.model.StoryModel

class DetailStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dataStory: StoryModel? = intent.getParcelableExtra("EXTRA_STORY")

        if (dataStory != null) {
            binding.txtDetailNameStory.text = dataStory.name

            Glide.with(this)
                .load(dataStory.photoUrl)
                .circleCrop()
                .into(binding.imgDetailStory)

            binding.txtDetailContentStory.text = dataStory.description
        }

    }
}