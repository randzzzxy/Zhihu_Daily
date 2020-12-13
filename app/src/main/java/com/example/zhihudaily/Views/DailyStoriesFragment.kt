package com.example.zhihudaily.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zhihudaily.ImageRotatorAdapter
import com.example.zhihudaily.ViewModels.DailyStoriesViewModel
import com.example.zhihudaily.R
import com.example.zhihudaily.StoriesAdapter
import kotlinx.android.synthetic.main.daily_stories_fragment.*

class DailyStoriesFragment : Fragment() {
    companion object {
        fun newInstance() = DailyStoriesFragment()
    }

    private val viewModel by activityViewModels<DailyStoriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.daily_stories_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //抓取数据
        viewModel.fetData()
        //设置recyclerView适配器并且设置数据
        val storiesAdapter = StoriesAdapter(viewModel)
        stories_recyclerView.apply {
            adapter = storiesAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        //viewPager
        val topStoriesAdapter = ImageRotatorAdapter()
        top_stories_viewPager2.adapter = topStoriesAdapter

        activity?.let {
            viewModel.storiesListLive.observe(it, Observer {
                storiesAdapter.submitList(it)
            })

            viewModel.topStoriesListLive.observe(it, Observer {
                topStoriesAdapter.submitList(it)
            })
        }
        // TODO: Use the ViewModel
    }

}