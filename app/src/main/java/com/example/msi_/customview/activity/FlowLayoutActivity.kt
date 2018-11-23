package com.example.msi_.customview.activity

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.msi_.customview.R
import com.example.msi_.customview.base.BaseActivity
import com.example.msi_.customview.view.FlowLayout

/**
 *@Author：ZC
 *@Time： 2018/10/23 16:47
 *@Description：自动填充换行的Layout展示
 **/
class FlowLayoutActivity : BaseActivity(){
    private lateinit var flowLayout: FlowLayout
 
    override fun getLayoutId() = R.layout.activity_flow_layout

    override fun initView() {
        flowLayout = findViewById(R.id.fl_content)
    }

    override fun init() {
        var view:TextView
        for (i in 0 until 10){
            view = LayoutInflater.from(this)
                    .inflate(R.layout.v_fl_content,flowLayout,false) as TextView
            view.setText("$i")
            flowLayout.addView(view)
        }
    }

    override fun initToolsbar() {
        title = "自动填充换行的Layout展示"
    }
}