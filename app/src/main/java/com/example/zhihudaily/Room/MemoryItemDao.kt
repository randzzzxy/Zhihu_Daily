package com.example.zhihudaily.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.zhihudaily.Models.MemoryItem

@Dao
interface MemoryItemDao {
    @Insert
    fun insertItems(vararg item: MemoryItem?)
    @Delete
    fun deleteWords(vararg item: MemoryItem?)


    @Query("SELECT * FROM STORY ORDER BY ID DESC")
    fun selectAllStories(): LiveData<List<MemoryItem?>?>?

}