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
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import java.io.IOException

class SectionViewModel(application: Application) : AndroidViewModel(application) {
    //数据库
    val storyRepository =
        StoryRepository(application)
    //首页的数据
    private val _sectionListLive = MutableLiveData<List<Section_Story>>()
    val sectionListLive: LiveData<List<Section_Story>>
    get() = _sectionListLive

    fun fetData(index: Int){
        val client = OkHttpClient()
        val request = okhttp3.Request.Builder().url(getUrl(index)).build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("hello",e.toString())
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                response.body?.string().let {
                    Log.d("hello", it + "")
                    //获取首页数据
                    _sectionListLive.value = Gson().fromJson(it,
                        Section_List::class.java).stories.toList()
                }
            }

        })
    }


    private fun getUrl(index:Int):String{
        return "https://news-at.zhihu.com/api/3/section/" + index.toString()
    }
}