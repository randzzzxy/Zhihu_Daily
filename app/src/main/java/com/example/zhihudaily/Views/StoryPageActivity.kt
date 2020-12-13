package com.example.zhihudaily.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.zhihudaily.ViewModels.DailyStoriesViewModel


class StoryPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel:DailyStoriesViewModel by viewModels()
        viewModel.fetData()
        val webView:WebView = WebView(this)
        setContentView(webView)
        val index = intent.getIntExtra("index",0)
        viewModel.storiesListLive.observe(this, Observer {
            webView.apply {
                getSettings().setJavaScriptEnabled(true)
                getSettings().setDefaultTextEncodingName("utf-8")
                if (index != null) {
                    viewModel.storiesListLive.value?.get(index)?.url?.let { loadUrl(it) }
                }
            }
        })

    }


}