package com.example.zhihudaily.Views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zhihudaily.R
import com.example.zhihudaily.SectionsAdapter
import com.example.zhihudaily.ViewModels.SectionsViewModel
import kotlinx.android.synthetic.main.sections_fragment.*

class SectionsFragment : Fragment() {

    companion object {
        fun newInstance() = SectionsFragment()
    }

    private lateinit var viewModel: SectionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sections_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SectionsViewModel::class.java)
        val adapter = SectionsAdapter()
        sections_recyclerView.adapter = adapter
        sections_recyclerView.layoutManager = LinearLayoutManager(context)
        activity?.let {
            viewModel.sectionsListLive.observe(it, Observer {
                adapter.submitList(it)
            })
        }
        viewModel.fetData()
        // TODO: Use the ViewModel
    }

}