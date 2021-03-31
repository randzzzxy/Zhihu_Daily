package com.example.zhihudaily

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.util.Log
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * 将crash信息保存在手机本地
 */
class MyCrashHandler : Thread.UncaughtExceptionHandler {
    private var mDefaultCrashHandler: Thread.UncaughtExceptionHandler? = null
    private var mContext: Context? = null
    fun init(context: Context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
        mContext = context.getApplicationContext()
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     */
    override fun uncaughtException(thread: Thread?, ex: Throwable) {
        try {
            //导出异常信息到SD卡中
            dumpExceptionToSDCard(ex)
            uploadExceptionToServer()
            //这里可以通过网络上传异常信息到服务器（完成下面的方法uploadExceptionToServer），便于开发人员分析日志从而解决bug
        } catch (e: IOException) {
            e.printStackTrace()
        }
        ex.printStackTrace()
        // 发生crash之后，需要将进程杀掉，因为此时程序不能继续往下运行，程序状态已不对
        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler!!.uncaughtException(thread, ex)
        } else {
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    @Throws(IOException::class)
    private fun dumpExceptionToSDCard(ex: Throwable) {
        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                Log.w(TAG, "sdcard unmounted,skip dump exception")
                return
            }
        }
        val dir = File(PATH)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val current = System.currentTimeMillis()
        val time: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(current))
        val file =
            File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX)
        try {
            val pw = PrintWriter(BufferedWriter(FileWriter(file)))
            pw.println(time)
            dumpPhoneInfo(pw)
            pw.println()
            ex.printStackTrace(pw)
            pw.close()
        } catch (e: Exception) {
            Log.e(TAG, "dump crash info failed")
        }
    }

    @Throws(PackageManager.NameNotFoundException::class)
    private fun dumpPhoneInfo(pw: PrintWriter) {
        val pm: PackageManager = mContext!!.getPackageManager()
        val pi: PackageInfo =
            pm.getPackageInfo(mContext!!.getPackageName(), PackageManager.GET_ACTIVITIES)
        pw.print("App Version: ")
        pw.print(pi.versionName)
        pw.print('_')
        pw.println(pi.versionCode)

        //android版本号
        pw.print("OS Version: ")
        pw.print(Build.VERSION.RELEASE)
        pw.print("_")
        pw.println(Build.VERSION.SDK_INT)

        //手机制造商
        pw.print("Vendor: ")
        pw.println(Build.MANUFACTURER)

        //手机型号
        pw.print("Model: ")
        pw.println(Build.MODEL)

        //cpu架构
        pw.print("CPU ABI: ")
        pw.println(Build.CPU_ABI)
    }

    private fun uploadExceptionToServer() {
        //TODO Upload Exception Message To Your Web Server
    }

    companion object {
        private const val TAG = "CrashHandler"
        private const val DEBUG = true
        private val PATH: String =
            Environment.getExternalStorageDirectory().getPath().toString() + "/CrashTest/log/"
        private const val FILE_NAME = "crash"
        private const val FILE_NAME_SUFFIX = ".trace"
        val instance = MyCrashHandler()
    }

}
