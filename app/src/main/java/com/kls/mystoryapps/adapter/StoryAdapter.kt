package com.kls.mystoryapps.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kls.mystoryapps.activity.DetailStoryActivity
import com.kls.mystoryapps.databinding.ItemStoryBinding
import com.kls.mystoryapps.model.StoryModel

class StoryAdapter(private val listUser: ArrayList<StoryModel>): RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size


    class ViewHolder(private val binding: ItemStoryBinding)  : RecyclerView.ViewHolder(binding.root){
        fun bind(storyModel: StoryModel){
            with(binding){
                txtAuthorStory.text = storyModel.name
                Glide.with(binding.root.context)
                    .load(storyModel.photoUrl)
                    .circleCrop()
                    .into(imgAuthorStory)

                binding.root.setOnClickListener {
                    val intent = Intent(binding.root.context, DetailStoryActivity::class.java)
                    intent.putExtra("EXTRA_STORY", storyModel)
                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            binding.root.context as Activity,
                            Pair(binding.imgAuthorStory, "imgDetail"),
                            Pair(binding.txtAuthorStory, "nameDetail"),
                        )
                    binding.root.context.startActivity(intent,optionsCompat.toBundle())
                }
            }
        }

    }
}