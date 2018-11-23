package com.example.msi_.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.jar.Attributes

/**
 *@Author：ZC
 *@Time： 2018/11/15 16:19
 *@Description：音量变化View
 **/

class VoiceView(context:Context,attributeSet: AttributeSet):View(context,attributeSet){

    private val paint = Paint()
    private var path:Path = Path()
    private var lineWidth = 20
    private var lineSpan = 10
    private var lineHeight = 15
    private var curHeight = 0f
    private var distance = 10f

    init {
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.parseColor("#5BBF21")
        paint.strokeWidth = 1f
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        for (i in 0..5) {
            path.reset()
            curHeight = (measuredHeight - i*(lineHeight+10)).toFloat()
            path.moveTo(0f,curHeight)
            path.lineTo((lineWidth+lineSpan*i).toFloat(),curHeight)
            curHeight -= lineHeight
            path.lineTo(lineWidth+lineSpan*i+distance,curHeight)
            path.lineTo(0f,curHeight)
            path.close()
            canvas!!.drawPath(path, paint)
        }
    }
}