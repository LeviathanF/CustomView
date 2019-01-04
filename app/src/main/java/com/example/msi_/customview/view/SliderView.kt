package com.example.msi_.customview.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.example.msi_.customview.R

/**
 *@Author：ZC
 *@Time： 2019/1/2 15:58
 *@Description：滑动块的动画
 **/
class SliderView(context: Context, attributeSet: AttributeSet):FrameLayout(context,attributeSet){
    private lateinit var sliderContent:View
    /**
     * 滑块状态
     */
    private var state = 0
    /**
     * 划分的数量
     */
    private val row:Int
    /**
     * 滑动持续时间
     */
    private val duration:Int

    init {
        val typeArray = context.obtainStyledAttributes(attributeSet,R.styleable.SliderView)
        row = typeArray.getInt(R.styleable.SliderView_row,3)
        duration = typeArray.getInt(R.styleable.SliderView_duration,300)
        typeArray.recycle()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        sliderContent = getChildAt(0)
        sliderContent.layout(measuredWidth/(row*2)-sliderContent.measuredWidth/2
                ,0,measuredWidth/(row*2)+sliderContent.measuredWidth/2,sliderContent.measuredHeight)
    }

    fun change(s:Int){
        (this.state != s).let {
            val offset = s - state
            sliderContent.animate().duration = duration.toLong()
            sliderContent.animate().translationX(offset * (measuredWidth / row).toFloat())
        }
    }
}