package com.example.msi_.customview.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.msi_.customview.R
import kotlin.math.max

/**
 *@Author：ZC
 *@Time： 2018/10/23 16:52
 *@Description：自动填充换行的Layout
 **/
class FlowLayout(context:Context,attributeSet: AttributeSet) :ViewGroup(context,attributeSet){
    private val TAG = "FlowLayout"
    private var span:Float = 0f

    init{
        val typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.FlowLayout)
//        获取自定义的子View间距
        span = typedArray.getDimension(R.styleable.FlowLayout_span,5f)
        Log.d("FlowLayout","$span")
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)

        var lineWidth = 0f
        var lineHeight = 0f
        var childWidth:Float
        var childHeight:Float
        var params:MarginLayoutParams
        var currentWidth = 0f
        var currentHeight = 0f

        var child:View
        for (i in 0 until childCount){
            child = getChildAt(i)
            if(child.visibility == View.GONE){
                return
            }

//            测量子view尺寸
            measureChild(child,widthMeasureSpec,heightMeasureSpec)

            params = child.layoutParams as MarginLayoutParams

            childWidth = (params.width+params.leftMargin+params.rightMargin).toFloat()
            childHeight = (params.height+params.topMargin+params.bottomMargin).toFloat()
            Log.d("TAG","$i width=$childWidth height=$childHeight")

            if (currentWidth+childWidth > sizeWidth-paddingLeft-paddingRight){
                currentWidth = childWidth
                lineHeight += currentHeight
                lineWidth = max(lineWidth,currentWidth)
            }else{
                currentHeight = max(currentHeight,childHeight)
            }
        }

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var childLeft = left+span.toInt()
        var childTop = top+paddingTop
        var childRight = childLeft
        var childBottom = 0

        val width = right - left

        var child:View
        for (i in 0 until childCount){
            child = getChildAt(i)

            if (childRight+child.width+span*2>width){
                childTop += child.height
                childLeft = left+span.toInt()
                childRight = childLeft+child.width
                childBottom = childTop+child.height
            }else{
                childLeft += span.toInt()+child.width
                childRight = childLeft+child.width
                childBottom = maxOf(childBottom,child.height)
            }
            child.layout(childLeft,childTop,childRight,childBottom)
        }
    }

}