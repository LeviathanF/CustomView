package com.example.msi_.customview.activity

import android.widget.TextView
import com.example.msi_.customview.R
import com.example.msi_.customview.base.BaseActivity
import com.example.msi_.customview.view.SliderView

/**
 *@Author：ZC
 *@Time： 2019/1/2 15:53
 *@Description：滑块Activity
 **/
class SliderActivity :BaseActivity(){
    private lateinit var tvOne:TextView
    private lateinit var tvSecond:TextView
    private lateinit var tvThird:TextView
    private lateinit var sliderView: SliderView

    override fun getLayoutId() = R.layout.activity_slider

    override fun initView() {
        tvOne = findViewById(R.id.tv_one)
        tvSecond = findViewById(R.id.tv_second)
        tvThird = findViewById(R.id.tv_third)
        sliderView = findViewById(R.id.sl_view)
    }

    override fun init() {
        tvOne.setOnClickListener {
            sliderView.change(0)
        }
        tvSecond.setOnClickListener {
            sliderView.change(1)
        }
        tvThird.setOnClickListener {
            sliderView.change(2)
        }

    }

    override fun initToolsbar() {
    }
}