package com.example.msi_.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 *@Author：ZC
 *@Time： 2018/11/22 11:07
 *@Description：
 **/
class MoveView(context:Context,attributeSet: AttributeSet):View(context,attributeSet){

    private val paint = Paint()
    private var offsetX = 0f
    private var offsetY = 0f
    private var lastX = 0f
    private var lastY = 0f
    private var screenHeight = 0
    private var screenWidth = 0
    private lateinit var scope:IntArray
    private val path = Path()

    init {
        paint.color = Color.BLUE
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL_AND_STROKE
        path.addCircle(50f,50f,50f, Path.Direction.CW)
        getWidthPixels()
    }

    fun setScope(array: IntArray){
        scope = array
    }

    /**
     * 获取屏幕宽度
     */
    private fun getWidthPixels() {
        val resources = this.resources
        val dm = resources.displayMetrics
        screenHeight = dm.heightPixels
        screenWidth = dm.widthPixels
    }

    /**
     * 使用layout改变位置
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                lastX = event.x
                lastY = event.y
            }
            MotionEvent.ACTION_MOVE ->{
                offsetX = event.x - lastX
                offsetY = event.y - lastY
//                offsetLeftAndRight(offsetX.toInt())
//                offsetTopAndBottom(offsetY.toInt())
//                左上角
                if (top+offsetY<0&&left+offsetX.toInt()<0){
                    layout(0,0,measuredWidth,measuredHeight)
                    return true
                }
//                右上角
                if (top+offsetY<0&&right+offsetX>scope[0]){
                    layout(scope[0]-measuredWidth,0,scope[0],measuredHeight)
                    return true
                }
//                左下角
                if (left+offsetX<0&&bottom+offsetY.toInt()>scope[1]){
                    layout(0,scope[1]-measuredHeight,measuredWidth,scope[1])
                    return true
                }
//                右下角
                if (bottom+offsetY>scope[1]&&right+offsetX>scope[0]){
                    layout(scope[0]-measuredWidth,scope[1]-measuredHeight,scope[0],scope[1])
                    return true
                }
                if (top+offsetY<0&&left+offsetX.toInt()<0){
                    layout(0,0,measuredWidth,measuredWidth)
                    return true
                }
                if (bottom+offsetY>scope[1]){
                    layout(left+offsetX.toInt(),scope[1]-measuredHeight,right+offsetX.toInt(),scope[1])
                    return true
                }
                if (top+offsetY<0){
                    layout(left+offsetX.toInt(),0,right+offsetX.toInt(),measuredHeight)
                    return true
                }
                if (left+offsetX.toInt()<0){
                    layout(0,top+offsetY.toInt(),measuredWidth,bottom+offsetY.toInt())
                    return true
                }
                if (right+offsetX.toInt()>screenWidth){
                    layout(screenWidth-measuredWidth,top+offsetY.toInt(),screenWidth,bottom+offsetY.toInt())
                    return true
                }
                layout(left+offsetX.toInt(),top+offsetY.toInt(),right+offsetX.toInt(),bottom+offsetY.toInt())
            }
        }
        return true
    }

//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        when(event.action){
//            MotionEvent.ACTION_DOWN ->{
//                lastX = event.x
//                lastY = event.y
//            }
//            MotionEvent.ACTION_MOVE ->{
//                offsetX = lastX - event.x
//                offsetY = lastY - event.y
//                scrollTo(offsetX.toInt(),offsetY.toInt())
//                lastX = event.x
//                lastY = event.y
//            }
//        }
//        return true
//    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path,paint)
    }
}