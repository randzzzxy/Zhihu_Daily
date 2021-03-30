package com.example.zhihudaily.Room

import android.os.AsyncTask
import com.example.zhihudaily.Models.MemoryItem
import java.lang.ref.WeakReference
import java.util.concurrent.ConcurrentHashMap

class AsyncTaskUtil {
    companion object {
        private val taskMap: MutableMap<String, HashMap<Int, WeakReference<AsyncTask<MemoryItem?, Void?, Boolean?>>>> =
            ConcurrentHashMap()

        fun addTask(obj: Array<out MemoryItem>, task: AsyncTask<MemoryItem?, Void?, Boolean?>) {
            val tag = obj.hashCode()
            var map = taskMap.get(tag.toString())
            if (null == map) {
                map = HashMap()
                map.put(task.hashCode(), WeakReference(task))
                taskMap.put(tag.toString(), map);
            } else {
                map.put(task.hashCode(), WeakReference(task))
            }
        }

        fun destroy() {
            taskMap.entries.forEach {
                it.value.entries.forEach {
                    if(it.value.get() != null){
                        it.value.get()!!.cancel(true)
                    }
                }
            }
        }
    }

}