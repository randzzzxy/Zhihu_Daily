package com.example.zhihudaily.Room

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.zhihudaily.Models.MemoryItem
import java.lang.Exception

class StoryRepository {

    companion object{
        private lateinit var storyDao: MemoryItemDao
    }

    constructor(context:Context) {
        storyDao = StoryDataBase.getInstance(context).getMemoryItemDao()
    }

    fun getStories(): LiveData<List<MemoryItem?>?>? {
        return storyDao.selectAllStories()
    }

    fun insertStories(vararg stories: MemoryItem, callback: (result:Boolean) -> Unit) {
        val task = InsertAsyncTask(callback).execute(*stories)
        AsyncTaskUtil.addTask(stories,task)
    }

    fun deleteStories(vararg stories: MemoryItem,
                      callback: (result:Boolean) -> Unit) {
        val task = DeleteAsyncTask(callback).execute(*stories)
        AsyncTaskUtil.addTask(stories,task)
    }

    fun destroy(){
        AsyncTaskUtil.destroy()
    }


    class InsertAsyncTask(callback1: (result:Boolean) -> Unit) : AsyncTask<MemoryItem?, Void?, Boolean?>() {
        lateinit var callBack:(result:Boolean) -> Unit
        lateinit var tag : String
        init {
            callBack = callback1
        }
        override fun doInBackground(vararg stories: MemoryItem?): Boolean? {
            tag = stories.hashCode().toString()
            try {
                storyDao.insertItems(*stories)
            }catch (e : Exception){
                return false
            }
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            if (result != null) {
                callBack(result)
            }
            AsyncTaskUtil.removeTask(tag,this)
        }
    }

    class DeleteAsyncTask(callback1: (result:Boolean) -> Unit): AsyncTask<MemoryItem?, Void?, Boolean?>() {
        lateinit var callBack:(result:Boolean) -> Unit
        lateinit var tag : String
        init {
            this.callBack = callback1
        }
        override fun doInBackground(vararg stories: MemoryItem?): Boolean? {
            tag = stories.hashCode().toString()
            try {
                storyDao.deleteWords(*stories)
            }catch (e : Exception){
                return false
            }
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            if (result != null) {
                callBack(result)
            }
            AsyncTaskUtil.removeTask(tag,this)
        }
    }


}