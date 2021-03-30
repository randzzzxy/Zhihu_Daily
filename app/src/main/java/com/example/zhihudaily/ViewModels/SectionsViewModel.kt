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
import com.example.zhihudaily.Models.Section
import com.example.zhihudaily.Models.Sections
import com.example.zhihudaily.Models.ZhihuDaily
import com.example.zhihudaily.VolleySingleton
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import java.io.IOException

class SectionsViewModel(application: Application) : AndroidViewModel(application) {
    private val _sectionsListLive = MutableLiveData<List<Section>>()
    val sectionsListLive: LiveData<List<Section>>
        get() = _sectionsListLive

    fun fetData(){
        val client = OkHttpClient()
        val request = okhttp3.Request.Builder().url(getUrl()).build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("hello",e.toString())
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                response.body?.string().let {
                    Log.d("hello", it + "")
                    //获取首页数据
                    _sectionsListLive.postValue(Gson().fromJson(it, Sections::class.java).data.toList())
                }
            }

        })
    }

    private fun getUrl():String{
        return "https://news-at.zhihu.com/api/3/sections"
    }
}