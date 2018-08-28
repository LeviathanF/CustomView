package com.example.msi_.customview.bean

import android.app.Activity
import android.support.v7.app.AppCompatActivity

/**
 *@Author：ZC
 *@Time： 2018/7/30 9:49
 *@Description：
 **/
data class RVBean(
        val name: String,
        val activity: Class<*>,
        val mode: Int = 0
)