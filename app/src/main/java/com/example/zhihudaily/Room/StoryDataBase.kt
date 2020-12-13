package com.example.zhihudaily.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.zhihudaily.Models.MemoryItem

@Database(entities = [MemoryItem::class], version = 1, exportSchema = false)
public abstract class StoryDataBase :RoomDatabase(){
    companion object{
        private var INSTANCE : StoryDataBase?=null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(context.applicationContext,
                    StoryDataBase::class.java,"story_database").build().also {
                    INSTANCE = it
                }
            }
    }

    abstract fun getMemoryItemDao(): MemoryItemDao
}