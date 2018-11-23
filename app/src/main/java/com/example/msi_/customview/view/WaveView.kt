package com.example.msi_.customview.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

/**
 *@Author：ZC
 *@Time： 2018/7/27 15:03
 *@Description：自定义使用贝塞尔曲线绘制波浪
 **/
class WaveView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {
    //        初始化前置波浪的paint
    private var paint: Paint = Paint()
    //        初始化后置波浪的paint
    private var sPaint: Paint = Paint()
    private var path: Path
    private lateinit var bottomPath: Path
    private val waveHeight = 70
    private var windowWidth = 0
    private var windowHeight = 0
    private val waveWidth = 900f
    private var baseLine = 0f
    private var offset = 0f
    private val distance = 150f

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.parseColor("#FB8300")
        paint.strokeWidth = 1f
        paint.isAntiAlias = true
        sPaint.style = Paint.Style.FILL_AND_STROKE
        sPaint.color = Color.parseColor("#ffa02f")
        sPaint.strokeWidth = 1f
        sPaint.isAntiAlias = true
        path = Path()
    }

    /**
     * 绘制底部固定部分
     * 降低GPU使用
     */
    private fun initBottomPath(){
        baseLine = windowHeight.toFloat()-200
        bottomPath = Path()
        bottomPath.moveTo(0f,baseLine+waveHeight-10)
        bottomPath.lineTo(windowWidth.toFloat(),baseLine+waveHeight-10)
        bottomPath.lineTo(windowWidth.toFloat(),windowHeight.toFloat())
        bottomPath.lineTo(0f,windowHeight.toFloat())
        bottomPath.close()
    }

    /**
     * 初始化波浪部分path
     */
    private fun initPath(distance:Float=0f,speed:Float = 1f):Path{
        var startX:Float
        path.reset()
        path.moveTo(windowWidth.toFloat(),baseLine)
//        波浪部分要超出屏幕现有宽度，不然会导致不连续
        for (i in -1..2){
            startX = windowWidth.toFloat()-waveWidth*(i-1)
            path.quadTo(startX-waveWidth/4-offset*speed-distance,baseLine-waveHeight,startX-waveWidth/2-offset*speed-distance,baseLine)
            path.quadTo(startX-waveWidth*0.75f-offset*speed-distance,baseLine+waveHeight,startX-waveWidth-offset*speed-distance,baseLine)
        }
        path.lineTo(0f,baseLine+waveHeight)
        path.lineTo(windowWidth.toFloat(),baseLine+waveHeight)
        path.close()
        return path
    }

    /**
     * 动画控制
     */
    private fun updateXControl(){
        val mAnimator = ValueAnimator.ofFloat(0f,waveWidth)
        mAnimator.interpolator = LinearInterpolator()
        mAnimator.addUpdateListener {
           offset = it.animatedValue as Float
            postInvalidate()
        }
//        持续时间
        mAnimator.duration = 16000
        mAnimator.repeatCount = ValueAnimator.INFINITE
        mAnimator.start()
    }

    /**
     * 获取屏幕高宽
     * 初始化动画 底部path避免在onDraw中每次重新计算底部path
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        windowWidth = measuredWidth
        windowHeight = measuredHeight
//        初始化动画
        updateXControl()
//        计算底部path
        initBottomPath()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(initPath(distance,2f),sPaint)
        canvas.drawPath(initPath(),paint)
        canvas.drawPath(bottomPath,paint)
    }
}