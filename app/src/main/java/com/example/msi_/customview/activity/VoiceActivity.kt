package com.example.msi_.customview.activity

import android.content.Context
import com.example.msi_.customview.R
import com.example.msi_.customview.base.BaseActivity
import android.util.DisplayMetrics
import android.content.Context.WINDOW_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log
import android.view.WindowManager



/**
 *@Author：ZC
 *@Time： 2018/11/15 16:17
 *@Description：音量大小变化图
 **/
class VoiceActivity: BaseActivity() {

    override fun getLayoutId() = R.layout.activity_voice

    override fun initView() {
    }

    override fun init() {
        getAndroiodScreenProperty()
    }

    override fun initToolsbar() {
    }

    fun getAndroiodScreenProperty() {
        val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels         // 屏幕宽度（像素）
        val height = dm.heightPixels       // 屏幕高度（像素）
        val density = dm.density         // 屏幕密度（0.75 / 1.0 / 1.5）
        val densityDpi = dm.densityDpi     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        val screenWidth = (width / density).toInt()  // 屏幕宽度(dp)
        val screenHeight = (height / density).toInt()// 屏幕高度(dp)


        Log.d("h_bl", "屏幕宽度（像素）：$width")
        Log.d("h_bl", "屏幕高度（像素）：$height")
        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：$density")
        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：$densityDpi")
        Log.d("h_bl", "屏幕宽度（dp）：$screenWidth")
        Log.d("h_bl", "屏幕高度（dp）：$screenHeight")
    }

}