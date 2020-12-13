package com.example.zhihudaily.Models

data class Section_List(val timestamp:Int,val stories:Array<Section_Story>)

data class Section_Story(val image_hue:String,val title:String,val url:String,val date:String,val display_date:String,val images:Array<String>,val id:Int)


