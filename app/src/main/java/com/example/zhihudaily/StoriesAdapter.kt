package com.example.zhihudaily

import android.content.Intent
import android.util.Log
import android.view.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.library.ZImageLoader
import com.example.zhihudaily.Models.MemoryItem
import com.example.zhihudaily.Models.Story
import com.example.zhihudaily.ViewModels.DailyStoriesViewModel
import com.example.zhihudaily.Views.StoryPageActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.image_cell.view.*
import kotlinx.android.synthetic.main.story_cell.view.*

class StoriesAdapter constructor(viewModel: DailyStoriesViewModel):ListAdapter<Story,MyViewHolder>(DIFFCALLBACK) {
    object DIFFCALLBACK: DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.id == newItem.id
        }
    }
    private lateinit var viewModel:DailyStoriesViewModel
    init {
        this.viewModel = viewModel
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.story_cell,parent,false))
        holder.itemView.setOnClickListener {  }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.apply {
            title_textView.text = getItem(position).title
            author_textView.text = getItem(position).hint
//            Glide.with(context).load(getItem(position).images[0]).into(little_imageView)
            ZImageLoader.with((context as FragmentActivity).lifecycle).load(getItem(position).images[0]).into(little_imageView)

        }

        holder.itemView.setOnTouchListener(object : View.OnTouchListener{
            val gestureDetector  = GestureDetector(holder.itemView.context,object:
                GestureDetector.SimpleOnGestureListener() {

                override fun onDoubleTap(e: MotionEvent?): Boolean {
                    val s = getItem(position)
                    val data = MemoryItem(s.id,s.title,s.hint,s.url,s.images[0])

                        viewModel.storyRepository.insertStories(data){
                            if(it){
                                Snackbar.make((holder.itemView.context as FragmentActivity).findViewById(R.id.fragment2),"故事已收藏",Snackbar.LENGTH_LONG)
                                    .setAction("撤销",object : View.OnClickListener{
                                        override fun onClick(v: View?) {
                                            Log.d("helllo", "撤销 ")
                                        }

                                    }).show()
                            }else{
                                Snackbar.make((holder.itemView.context as FragmentActivity).findViewById(R.id.fragment2),"已在收藏单",Snackbar.LENGTH_SHORT)
                                    .show()
                            }
                        }


                    return true
                }

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    val intent = Intent(holder.itemView.context,StoryPageActivity::class.java)
                    intent.putExtra("index",holder.adapterPosition)
                    holder.itemView.context.startActivity(intent)
                    return true
                }
            })
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return gestureDetector.onTouchEvent(event)
            }


        })

    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)