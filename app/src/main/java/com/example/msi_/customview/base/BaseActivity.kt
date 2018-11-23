package com.example.msi_.customview.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 *@Author：ZC
 *@Time： 2018/7/27 11:34
 *@Description：
 **/
abstract class BaseActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        init()
        initToolsbar()
        initRv()
    }

    abstract fun getLayoutId():Int

    abstract fun initView()

    abstract fun init()

    abstract fun initToolsbar()

    open fun initRv(){}
}