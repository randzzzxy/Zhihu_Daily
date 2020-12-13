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
import com.example.zhihudaily.VolleySingleton
import com.google.gson.Gson

class SectionsViewModel(application: Application) : AndroidViewModel(application) {
    private val _sectionsListLive = MutableLiveData<List<Section>>()
    val sectionsListLive: LiveData<List<Section>>
        get() = _sectionsListLive

    fun fetData(){
        val stringRequest = StringRequest(
            Request.Method.GET,
            getUrl(),
            Response.Listener {
                _sectionsListLive.value = Gson().fromJson(it, Sections::class.java).data.toList()
            },
            Response.ErrorListener {
                Log.d("sections", it.toString())
            }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }

    private fun getUrl():String{
        return "https://news-at.zhihu.com/api/3/sections"
    }
}