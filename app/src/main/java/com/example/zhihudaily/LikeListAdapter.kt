package com.example.zhihudaily

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.library.ZImageLoader
import com.example.zhihudaily.Models.MemoryItem
import com.example.zhihudaily.Models.Section_Story
import com.example.zhihudaily.Views.SectionStoryActivity
import com.example.zhihudaily.Views.StoryPageActivity
import kotlinx.android.synthetic.main.image_cell.view.*
import kotlinx.android.synthetic.main.story_cell.view.*

class LikeListAdapter: ListAdapter<MemoryItem, MyViewHolder>(DIFFCALLBACK) {
    object DIFFCALLBACK: DiffUtil.ItemCallback<MemoryItem>() {
        override fun areItemsTheSame(oldItem: MemoryItem, newItem: MemoryItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MemoryItem, newItem: MemoryItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.story_cell,parent,false))
        holder.itemView.setOnClickListener {  }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.apply {
            title_textView.text = getItem(position).title
            author_textView.text = getItem(position).info
//            Glide.with(context).load(getItem(position).image).into(little_imageView)
            ZImageLoader.with((context as FragmentActivity).lifecycle).load(getItem(position).image).into(little_imageView)

        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, SectionStoryActivity::class.java)
            intent.putExtra("url",getItem(position).url)
            it.context.startActivity(intent)
        }
    }
}