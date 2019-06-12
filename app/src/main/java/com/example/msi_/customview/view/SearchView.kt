package com.example.msi_.customview.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator

/**
 *@Author：ZC
 *@Time： 2019/6/11 16:51
 *@Description：搜索View动画展示
 **/

class SearchView(context: Context,attributeSet: AttributeSet):View(context,attributeSet){
    private val paint = Paint()
    private val sPath = Path()
    private val tPath = Path()
    private val pathMeasure = PathMeasure()
    private val animator = ValueAnimator.ofFloat(0f,1f)
    private var value = 0f

    init {
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        paint.isAntiAlias = true

//        添加搜索的圆圈时,不要绘制整个圆,否则getSegment方法无法获取后续的路径
        sPath.addArc(0f,0f,100f,100f,0f,359.9f)
        sPath.lineTo(150f,50f)

        pathMeasure.setPath(sPath,true)

        animator.duration = 2000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            value = it.animatedValue as Float
            if (value>0.98f){
                value = 1f
            }
            invalidate()
        }
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        pathMeasure.getSegment(0f,value*pathMeasure.length,tPath,true)
        if (value == 1f){
            tPath.reset()
        }
        canvas?.let {
            canvas.save()
            canvas.translate(150f,150f)
            canvas.rotate(45f)
            canvas.drawPath(tPath,paint)
            canvas.restore()
        }
    }
}