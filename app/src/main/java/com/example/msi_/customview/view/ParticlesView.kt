package com.example.msi_.customview.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.msi_.customview.R
import com.example.msi_.customview.bean.ParticleBean

/**
 *@Author：ZC
 *@Time： 2019/6/12 17:05
 *@Description：粒子爆炸效果自定义View
 **/
class ParticlesView(context: Context,attributeSet: AttributeSet):View(context,attributeSet){
    private val pathList = arrayListOf<ParticleBean>()
    private val bitmap = BitmapFactory.decodeResource(resources,R.drawable.bg)
    private val paint = Paint()

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true

    }

}