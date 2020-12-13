package com.example.zhihudaily.Views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zhihudaily.Models.MemoryItem
import com.example.zhihudaily.Models.Section_Story
import com.example.zhihudaily.R
import com.example.zhihudaily.SectionListAdapter
import com.example.zhihudaily.ViewModels.SectionViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.section_fragment.*

class SectionFragment : Fragment() {

    companion object {
        fun newInstance() = SectionFragment()
    }

    private lateinit var viewModel: SectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.section_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SectionViewModel::class.java)
        //抓取数据
        arguments?.getInt("position")?.let { viewModel.fetData(it) }
        //设置recyclerView适配器并且设置数据
        val sectionListAdapter = SectionListAdapter(viewModel)
        sectionList_recyclerView.apply {
            adapter = sectionListAdapter
            layoutManager = LinearLayoutManager(activity)
        }


        activity?.let {
            viewModel.sectionListLive.observe(it, Observer {
                sectionListAdapter.submitList(it)
            })
        }



    }

}