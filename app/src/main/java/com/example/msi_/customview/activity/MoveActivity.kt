package com.example.msi_.customview.activity

import android.widget.FrameLayout
import com.example.msi_.customview.R
import com.example.msi_.customview.base.BaseActivity
import com.example.msi_.customview.view.MoveView

/**
 *@Author：ZC
 *@Time： 2018/11/22 11:01
 *@Description：
 **/

class MoveActivity:BaseActivity(){
    private lateinit var flTop:FrameLayout
    private lateinit var mv:MoveView
    private var location = intArrayOf(0,0)

    override fun getLayoutId(): Int = R.layout.activity_move

    override fun initView() {
        flTop = findViewById(R.id.fl_top)
        mv = findViewById(R.id.mv)
    }

    override fun init() {
        val observer = flTop.viewTreeObserver
        observer.addOnGlobalLayoutListener {
//            flTop.getLocationOnScreen(location)
//            location[2] = location[0]+flTop.measuredWidth
//            location[3] = location[1]+flTop.measuredHeight
            location[0] = flTop.measuredWidth
            location[1] = flTop.measuredHeight
            mv.setScope(location)
        }
    }

    override fun initToolsbar() {
    }

}