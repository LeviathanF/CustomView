package com.example.msi_.customview.view

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator

/**
 *@Author：ZC
 *@Time： 2019/6/11 14:12
 *@Description：Loading动画
 **/

class LoadingBView(context: Context, attributeSet: AttributeSet):View(context,attributeSet){
    private val paint = Paint()
    private val sPath = Path()
    private val tPath = Path()
    private val pathMeasure = PathMeasure()
    private val animator = ValueAnimator.ofFloat(0f,1f)
    private var value = 0f
    private var start = 0f

    init {
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        paint.strokeWidth = 10f
        paint.isAntiAlias = true
        sPath.addCircle(150f,150f,100f,Path.Direction.CCW)
        pathMeasure.setPath(sPath,true)

        animator.duration = 1000
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener {
            value = it.animatedValue as Float
//            由于刷新率的原因,很难捕捉到1
            if (value > 0.98f){
                value = 1f
            }
            invalidate()
        }
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        tPath.reset()
        start = if (value<0.5){
            0f
        }else{
            2*value - 1
        }

        pathMeasure.getSegment(start*pathMeasure.length,value*pathMeasure.length,tPath,true)
        canvas?.drawPath(tPath,paint)
//        if (value == 1f){
//            tPath.reset()
//        }
    }
}