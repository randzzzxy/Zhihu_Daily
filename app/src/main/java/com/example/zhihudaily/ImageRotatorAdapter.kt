package com.example.zhihudaily

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.library.ZImageLoader
import com.example.zhihudaily.Models.Top_Story
import kotlinx.android.synthetic.main.image_cell.view.*


class ImageRotatorAdapter: ListAdapter<Top_Story,MyViewHolder2>(DIFFCALLBACK) {
    object DIFFCALLBACK: DiffUtil.ItemCallback<Top_Story>() {
        override fun areItemsTheSame(oldItem: Top_Story, newItem: Top_Story): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Top_Story, newItem: Top_Story): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_cell,parent,false)
        val holder = MyViewHolder2(view)
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        holder.itemView.apply {
            //实现圆角
//            Glide.with(context).load(getItem(position).image).into(imageView)
            ZImageLoader.with((context as FragmentActivity).lifecycle).load(getItem(position).image).into(imageView)
            Log.d("sadsad", "onBindViewHolder: " + getItem(position).image)
        }
    }

}

class MyViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView)