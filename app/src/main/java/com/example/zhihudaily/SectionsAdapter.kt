package com.example.zhihudaily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.library.ZImageLoader
import com.example.zhihudaily.Models.Section
import com.example.zhihudaily.Models.Top_Story
import kotlinx.android.synthetic.main.image_cell.view.*
import kotlinx.android.synthetic.main.story_cell.view.*


class SectionsAdapter: ListAdapter<Section,MyViewHolder>(DIFFCALLBACK) {
    object DIFFCALLBACK: DiffUtil.ItemCallback<Section>() {
        override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.story_cell,parent,false)
        val holder = MyViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val section = getItem(position)
        holder.itemView.apply {
            title_textView.text = section.name
            author_textView.text = section.description
//            Glide.with(context).load(section.thumbnail).into(little_imageView)
            ZImageLoader.with((context as FragmentActivity).lifecycle).load(section.thumbnail).into(little_imageView)

        }
        holder.itemView.setOnClickListener {
            Bundle().apply {
                putInt("position",getItem(position).id)
                holder.itemView.findNavController().navigate(R.id.action_sectionsFragment_to_sectionFragment,this)
            }
        }
    }
}