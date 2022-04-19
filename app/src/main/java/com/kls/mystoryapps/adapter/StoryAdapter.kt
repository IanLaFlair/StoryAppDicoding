package com.kls.mystoryapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
            }
        }

    }
}