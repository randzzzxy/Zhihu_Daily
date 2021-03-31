package com.example.zhihudaily.Application

import android.app.Application
import com.example.zhihudaily.MyCrashHandler

class MyApplication : Application() {
    private var sInstance: MyApplication? = null

    override fun onCreate() {
        super.onCreate()
        sInstance = this

        //在这里为应用设置异常处理程序，然后我们的程序才能捕获未处理的异常
        val crashHandler: MyCrashHandler = MyCrashHandler.instance
        crashHandler.init(this)
    }

    fun getInstance(): MyApplication? {
        return sInstance
    }
}