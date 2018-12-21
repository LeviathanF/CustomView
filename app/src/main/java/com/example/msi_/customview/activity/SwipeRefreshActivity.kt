package com.example.msi_.customview.activity

import android.widget.ImageView

import com.example.msi_.customview.R
import com.example.msi_.customview.base.BaseActivity

/**
 * @Author：ZC
 * @Time： 2018/12/18 11:13
 * @Description：滑动展示加载
 */
class SwipeRefreshActivity : BaseActivity() {
    private val ivLoad: ImageView? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_swiperefresh
    }

    override fun initView() {}

    override fun init() {

    }

    override fun initToolsbar() {
        title = "滑动展示加载"
    }
}
