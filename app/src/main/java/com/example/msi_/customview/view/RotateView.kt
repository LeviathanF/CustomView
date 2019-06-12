package com.example.msi_.customview.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

/**
 *@Author：ZC
 *@Time： 2019/6/11 16:05
 *@Description：绘制圆切线
 **/
class RotateView(context: Context,attributeSet: AttributeSet):View(context,attributeSet){
    private val paint = Paint()
    private val sPath = Path()
    private val pathMeasure = PathMeasure()
    private val animator = ValueAnimator.ofFloat(0f,1f)
    private var value = 0f
    private val mPos = FloatArray(2)
    private val mTan = FloatArray(2)
    private var degree = 0f

    init {
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        paint.isAntiAlias = true

        sPath.addCircle(0f,0f,100f,Path.Direction.CCW)
        pathMeasure.setPath(sPath,true)

        animator.duration = 3000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            value = it.animatedValue as Float
            invalidate()
        }
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        pathMeasure.getPosTan(value*pathMeasure.length,mPos,mTan)
        degree = (Math.atan2(mTan[1].toDouble(),mTan[0].toDouble())*180/Math.PI).toFloat()

        canvas?.save()
        canvas?.translate(100f,100f)
        canvas?.drawPath(sPath,paint)
        canvas?.drawCircle(mPos[0],mPos[1],20f,paint)
        canvas?.rotate(degree)
        canvas?.drawLine(0f, 100f, 150f, -100f, paint)
        canvas?.restore()
    }
}