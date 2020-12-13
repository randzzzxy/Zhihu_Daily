package com.example.zhihudaily

import android.content.Intent
import android.util.Log
import android.view.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.library.ZImageLoader
import com.example.zhihudaily.Models.MemoryItem
import com.example.zhihudaily.Models.Section_Story
import com.example.zhihudaily.ViewModels.SectionViewModel
import com.example.zhihudaily.Views.SectionStoryActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.image_cell.view.*
import kotlinx.android.synthetic.main.story_cell.view.*

class SectionListAdapter(viewModel:SectionViewModel): ListAdapter<Section_Story, MyViewHolder>(DIFFCALLBACK) {
    private lateinit var viewModel: SectionViewModel
    init {
        this.viewModel = viewModel
    }
    object DIFFCALLBACK: DiffUtil.ItemCallback<Section_Story>() {
        override fun areItemsTheSame(oldItem: Section_Story, newItem: Section_Story): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Section_Story, newItem: Section_Story): Boolean {
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
            author_textView.text = getItem(position).display_date
//            Glide.with(context).load(getItem(position).images[0]).into(little_imageView)
            ZImageLoader.with((context as FragmentActivity).lifecycle).load(getItem(position).images[0]).into(little_imageView)

        }


        holder.itemView.setOnTouchListener(object : View.OnTouchListener{
            val gestureDetector  = GestureDetector(holder.itemView.context,object:
                GestureDetector.SimpleOnGestureListener() {

                override fun onDoubleTap(e: MotionEvent?): Boolean {
                    val s = getItem(position)
                    val data = MemoryItem(s.id,s.title,s.display_date,s.url,s.images[0])

                    viewModel.storyRepository.insertStories(data){
                        if(it){
                            Snackbar.make((holder.itemView.context as FragmentActivity).findViewById(R.id.fragment2),"故事已收藏",
                                Snackbar.LENGTH_LONG)
                                .setAction("撤销",object : View.OnClickListener{
                                    override fun onClick(v: View?) {
                                        Log.d("helllo", "撤销 ")
                                    }

                                }).show()
                        }else{
                            Snackbar.make((holder.itemView.context as FragmentActivity).findViewById(R.id.fragment2),"已在收藏单",
                                Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }


                    return true
                }

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    val intent = Intent(holder.itemView.context, SectionStoryActivity::class.java)
                    intent.putExtra("url",getItem(position).url)
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