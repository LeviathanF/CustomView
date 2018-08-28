package com.example.msi_.customview.activity

import android.hardware.display.DisplayManager
import android.text.Layout
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.msi_.customview.R
import com.example.msi_.customview.base.BaseActivity
import com.example.msi_.customview.view.DynamicWave
import com.example.msi_.customview.view.WaveView

/**
 *@Author：ZC
 *@Time： 2018/7/30 10:01
 *@Description：
 **/

class WaveActivity:BaseActivity(){
    private lateinit var llContent:LinearLayout
    private lateinit var view:View

    override fun getLayoutId() = R.layout.activity_wave

    override fun initView() {
        llContent = findViewById(R.id.ll_content)
    }

    override fun init() {
        val mode = intent.getIntExtra("mode",0)
        when(mode){
            0 -> { view = DynamicWave(this,null) }
            1 -> { view = WaveView(this,null)}
        }
        llContent.addView(view)
    }

    override fun initToolsbar() {
        title = "波浪线展示"
    }

}