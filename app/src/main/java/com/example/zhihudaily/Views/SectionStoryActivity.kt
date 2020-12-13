package com.example.zhihudaily.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.zhihudaily.ViewModels.DailyStoriesViewModel
import com.example.zhihudaily.ViewModels.SectionViewModel


class SectionStoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra("url")
        val webView:WebView = WebView(this)
        setContentView(webView)
        webView.apply {
            getSettings().setJavaScriptEnabled(true)
            getSettings().setDefaultTextEncodingName("utf-8")
            if (url != null) {
                loadUrl(url)
            }
        }

    }

    private fun getHtml(content:String){

    }
}