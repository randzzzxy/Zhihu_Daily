package com.example.zhihudaily.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Story")
data class MemoryItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "info")
    val info:String,
    @ColumnInfo(name = "url")
    val url:String,
    @ColumnInfo(name = "image")
    val image:String
)