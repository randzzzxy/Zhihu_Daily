package com.example.zhihudaily.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.zhihudaily.Models.Section_List
import com.example.zhihudaily.Models.Section_Story
import com.example.zhihudaily.Models.Story
import com.example.zhihudaily.Models.ZhihuDaily
import com.example.zhihudaily.Room.StoryRepository
import com.example.zhihudaily.VolleySingleton
import com.google.gson.Gson

class SectionViewModel(application: Application) : AndroidViewModel(application) {
    //数据库
    val storyRepository =
        StoryRepository(application)
    //首页的数据
    private val _sectionListLive = MutableLiveData<List<Section_Story>>()
    val sectionListLive: LiveData<List<Section_Story>>
    get() = _sectionListLive

    fun fetData(index: Int){
        val stringRequest = StringRequest(
            Request.Method.GET,
            getUrl(index),
            Response.Listener {
                //获取首页数据
                _sectionListLive.value = Gson().fromJson(it,
                    Section_List::class.java).stories.toList()
            },
            Response.ErrorListener {
                Log.d("hello",it.toString())
            }
        )
        VolleySingleton.getInstance(
            getApplication()
        ).requestQueue.add(stringRequest)
    }


    private fun getUrl(index:Int):String{
        return "https://news-at.zhihu.com/api/3/section/" + index.toString()
    }
}