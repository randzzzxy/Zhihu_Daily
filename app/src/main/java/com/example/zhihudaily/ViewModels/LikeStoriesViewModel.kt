package com.example.zhihudaily.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.zhihudaily.Models.MemoryItem
import com.example.zhihudaily.Room.StoryRepository

class LikeStoriesViewModel(application: Application) : AndroidViewModel(application) {
    private val _storyRepository =
        StoryRepository(application)
    fun getStories(): LiveData<List<MemoryItem?>?>? {
        return _storyRepository.getStories()
    }

    fun deleteStoryItem(item: MemoryItem, callback: (result:Boolean) -> Unit){
        _storyRepository.deleteStories(item){
            callback(it)
        }
    }
    fun insertStoryItem(item: MemoryItem,callback: (result:Boolean) -> Unit){
        _storyRepository.insertStories(item){
            callback(it)
        }
    }

}