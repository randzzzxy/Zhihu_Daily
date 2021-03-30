package com.example.zhihudaily.Views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zhihudaily.LikeListAdapter
import com.example.zhihudaily.Models.MemoryItem
import com.example.zhihudaily.Models.Section_Story
import com.example.zhihudaily.ViewModels.LikeStoriesViewModel
import com.example.zhihudaily.R
import com.example.zhihudaily.StoriesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.daily_stories_fragment.*
import kotlinx.android.synthetic.main.like_stories_fragment.*
import kotlinx.android.synthetic.main.section_fragment.*

class LikeStoriesFragment : Fragment() {
    lateinit var list:List<MemoryItem>

    companion object {
        fun newInstance() = LikeStoriesFragment()
    }

    private lateinit var viewModel: LikeStoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.like_stories_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LikeStoriesViewModel::class.java)
        // TODO: Use the ViewModel

        val likeListAdapter = LikeListAdapter()
        like_recyclerView.apply {
            adapter = likeListAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        activity?.let {
            viewModel.getStories()?.observe(it, Observer {
                likeListAdapter.submitList(it)
                list = it as List<MemoryItem>
            })
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val storyToDelete: MemoryItem? =
                    list.get(viewHolder.adapterPosition)

                if (storyToDelete != null) {
                    viewModel.deleteStoryItem(storyToDelete) {
                        Snackbar.make(
                            requireActivity().findViewById(R.id.fragment2),
                            "删除了一个故事",
                            Snackbar.LENGTH_SHORT
                        ).setAction("撤销") {
                            viewModel.insertStoryItem(storyToDelete) {

                            }
                        }.show()
                    }
                }

            }

        }).attachToRecyclerView(like_recyclerView)
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.destroy()
    }
}