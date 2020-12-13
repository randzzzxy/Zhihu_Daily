package com.example.zhihudaily.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.zhihudaily.Models.*
import com.example.zhihudaily.Room.StoryRepository
import com.example.zhihudaily.VolleySingleton
import com.google.gson.Gson

class DailyStoriesViewModel(application: Application) : AndroidViewModel(application) {
    //数据库
    val storyRepository =
        StoryRepository(application)
    //首页的数据
    private val _storiesListLive = MutableLiveData<List<Story>>()
    val storiesListLive: LiveData<List<Story>>
    get() = _storiesListLive

    //轮播图的数据
    private val _topStoriesListLive = MutableLiveData<List<Top_Story>>()
    val topStoriesListLive: LiveData<List<Top_Story>>
    get() = _topStoriesListLive

    private val _story_currentLive = MutableLiveData<Page_Story>()
    val story_currentLive: LiveData<Page_Story>
    get() = _story_currentLive

    fun fetData(){
        val stringRequest = StringRequest(
            Request.Method.GET,
            getUrl(),
            Response.Listener {
                //获取首页数据
                _storiesListLive.value = Gson().fromJson(it,
                    ZhihuDaily::class.java).stories.toList()
                _topStoriesListLive.value = Gson().fromJson(it,
                    ZhihuDaily::class.java).top_stories.toList()
                Log.d("hello", (_storiesListLive.value!!.size).toString() + "")
            },
            Response.ErrorListener {
                Log.d("hello",it.toString())
            }
        )
        VolleySingleton.getInstance(
            getApplication()
        ).requestQueue.add(stringRequest)
    }

    fun fetData_stories(index:Int){
        val stringRequest = StringRequest(
            Request.Method.GET,
            getPageUrl(index),
            Response.Listener {
                Log.d("hello", it)
                _story_currentLive.value = Gson().fromJson(it,
                    Page_Story::class.java)
            },
            Response.ErrorListener {
                Log.d("hello",it.toString())
            }
        )
        Log.d("hello", getPageUrl(index))
        VolleySingleton.getInstance(
            getApplication()
        ).requestQueue.add(stringRequest)
    }

    private fun getPageUrl(index:Int):String{
        return "https://news-at.zhihu.com/api/4/news/" + index.toString()
    }

    private fun getUrl():String{
        return "https://news-at.zhihu.com/api/4/news/latest"
    }
}