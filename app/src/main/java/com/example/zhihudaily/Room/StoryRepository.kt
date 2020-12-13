package com.example.zhihudaily.Room

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.zhihudaily.Models.MemoryItem
import com.example.zhihudaily.Models.Section_Story
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
        insertAsyncTask(callback).execute(*stories)
    }

    fun deleteStories(vararg stories: MemoryItem,
                      callback: (result:Boolean) -> Unit) {
        deleteAsyncTask(callback).execute(*stories)
    }


    class insertAsyncTask(callback1: (result:Boolean) -> Unit) : AsyncTask<MemoryItem?, Void?, Boolean?>() {
        lateinit var callBack:(result:Boolean) -> Unit
        init {
            callBack = callback1
        }
        override fun doInBackground(vararg stories: MemoryItem?): Boolean? {
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
        }
    }

    class deleteAsyncTask(callback1: (result:Boolean) -> Unit): AsyncTask<MemoryItem?, Void?, Boolean?>() {
        lateinit var callBack:(result:Boolean) -> Unit
        init {
            this.callBack = callback1
        }
        override fun doInBackground(vararg stories: MemoryItem?): Boolean? {

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
        }
    }


}