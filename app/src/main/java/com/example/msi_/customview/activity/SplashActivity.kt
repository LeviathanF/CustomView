package com.example.msi_.customview.activity

import com.example.msi_.customview.R
import com.example.msi_.customview.base.BaseActivity

/**
 *@Author：ZC
 *@Time： 2019/6/12 9:38
 *@Description：加载跳转屏幕
 **/
class SplashActivity:BaseActivity() {

    override fun getLayoutId() = R.layout.activity_splash

    override fun initView() {
    }

    override fun init() {
    }

    override fun initToolsbar() {
        title = "过场动画"
    }
}