package com.example.msi_.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.abs
import kotlin.random.Random

/**
 *@Author：ZC
 *@Time： 2019/5/28 17:18
 *@Description：绘制椭圆
 **/
const val TAG = "OvalView"
const val MAX = 40
const val pathLoop = 12
class OvalView(context: Context,attributeSet: AttributeSet):View(context,attributeSet){

    private val paint = Paint()
    private val tPaint = Paint()
    private var windowWidth = 0
    private var windowHeight = 0
    private var startX = 0f
    private var tStartX = 0f
    private var tStartY = 0f
    private var tMidX = 0f
    private var tMidY = 0f
    private var tEndX = 0f
    private var tEndY = 0f
    private var startY = 0f
    private var unitHeight = 15
    private var tUnitHeight = unitHeight
    private var path = Path()
    private val velocityUnit = 6
    private var velocity = velocityUnit
    private var random = Random
    private var offsetY = 0
    private var offsetX = 0


    init {
        paint.color = Color.parseColor("#FB8300")
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        tPaint.color = Color.BLACK
        tPaint.style = Paint.Style.STROKE
        tPaint.isAntiAlias = true
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        windowWidth = measuredWidth
        startY = measuredHeight.toFloat() - 200
        windowHeight = measuredHeight
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.reset()
        Log.d(TAG,"start unitHeight:$unitHeight")
        tUnitHeight = unitHeight
        for (i in 0..0){
            path = initPath(path,tUnitHeight,startY)
        }
        unitHeight += velocity
        Log.d(TAG,"end unitHeight:$unitHeight")
        canvas?.drawPath(path,paint)
        if (unitHeight>=25){
            velocity = -velocityUnit
        }
        if (unitHeight<=-25){
            velocity = velocityUnit
        }
        postDelayed({
            invalidate()
        },100)
    }

    private fun initPath(path:Path,unitHeight:Int,startY:Float):Path{
        Log.d(TAG,"unitHeight:$unitHeight")
        path.moveTo(startX,startY)
//        offsetY = random.nextInt(3,10)
        tStartX = startX
        tStartY = startY
        for (i in 1..pathLoop){
            offsetX = pathLoop/2-abs(i-pathLoop/2)
            tMidX = tStartX+offsetX*(windowWidth/80)
            tMidY = if (i%2==0) {
//                tEndY = tStartY+offsetY*i
                tStartY + unitHeight*offsetX
            }else{
//                tEndY = tStartY-offsetY*i
                tStartY - unitHeight*offsetX
            }
            path.cubicTo(tStartX,tStartY,tMidX,tMidY,tMidX+offsetX*(windowWidth/80),tStartY)
            tStartX = tMidX+offsetX*(windowWidth/80)
        }
        return path
    }
}