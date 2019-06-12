package com.example.msi_.customview.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import com.example.msi_.customview.listener.StateView

/**
 *@Author：ZC
 *@Time： 2019/6/12 9:49
 *@Description：
 **/
const val SplashTAG = "splash"
class SplashView(context: Context,attributeSet: AttributeSet):View(context,attributeSet), StateView {
    private var mState:State? = null

    companion object{
//        初始化
        const val INIT = 0
//        扩散
        const val SPREAD = 1
//        展示
        const val SHOW = 2
//        结束
        const val END = 3
    }
    override fun changeState(state: Int) {
        when(state){
            INIT -> mState = InitState(this)
            SPREAD -> mState = SpreadState(this)
            SHOW -> mState = ShowState(this)
            END -> mState = null
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mState == null){
            mState = InitState(this)
        }
        mState?.draw(canvas)
    }

    /**
     * 状态接口
     */
    private interface State{
        fun draw(canvas: Canvas)
    }

    /**
     * 初始状态 绘制6个小球旋转
     */
    private class InitState(val view: StateView):State{
        private val colorList = arrayListOf<Int>()
        private val radius = 13f
        private val outRadius = 50f
        private val paint = Paint()
        private var tAngle:Double = 0.0
        private var angle = 0
        private var xTmp = 0f
        private var yTmp = 0f
//        设定一个基础角度方便制作旋转动画
        private var baseAngle = 0f
        private val animator = ValueAnimator.ofFloat(0f,1f)


        init {
            colorList.add(Color.RED)
            colorList.add(Color.GREEN)
            colorList.add(Color.BLUE)
            colorList.add(Color.RED)
            colorList.add(Color.GREEN)
            colorList.add(Color.BLUE)

            paint.style = Paint.Style.FILL_AND_STROKE
            paint.isAntiAlias = true

            angle = 360/colorList.size

//            设置动画
            animator.duration = 2000
            animator.repeatCount = 1
            animator.interpolator = LinearInterpolator()
            animator.addListener(object:Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    view.changeState(SPREAD)
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {}
            })
            animator.addUpdateListener{
//                每次改变基础的角度
                baseAngle = 360*(it.animatedValue as Float)
                view.invalidate()
            }
            animator.start()
        }
        override fun draw(canvas: Canvas) {
            canvas.drawColor(Color.WHITE)
            canvas.save()
            canvas.translate(canvas.width/2f,canvas.height/2f)
            colorList.forEachIndexed{index,color->
//                为小球设置颜色
                paint.color = color
//                获取小球圆心的偏移角度
                tAngle = (angle*index+baseAngle).toDouble()
//                计算小球圆心的X坐标值
                xTmp = (Math.cos(Math.toRadians(tAngle))*outRadius).toFloat()
//                计算小球圆心的Y坐标值
                yTmp = (Math.sin(Math.toRadians(tAngle))*outRadius).toFloat()
//                绘制小球
                canvas.drawCircle(xTmp,yTmp,radius,paint)
            }
            canvas.restore()
        }
    }

    /**
     * 扩散状态 绘制6个小球扩散
     */
    private class SpreadState(val view: StateView):State{
        private val colorList = arrayListOf<Int>()
        private val radius = 13f
        private val outRadius = 50f
        private val paint = Paint()
        private var tAngle:Double = 0.0
        private var angle = 0
        private var xTmp = 0f
        private var yTmp = 0f
        //        设定一个可变的半径
        private var tRadius = outRadius
        private val animator = ValueAnimator.ofFloat(1f,2.0f)


        init {
            colorList.add(Color.RED)
            colorList.add(Color.GREEN)
            colorList.add(Color.BLUE)
            colorList.add(Color.RED)
            colorList.add(Color.GREEN)
            colorList.add(Color.BLUE)

            paint.style = Paint.Style.FILL_AND_STROKE
            paint.isAntiAlias = true

            angle = 360/colorList.size

//            设置动画
            animator.duration = 1000
            animator.interpolator = LinearInterpolator()
            animator.addListener(object:Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    view.changeState(SHOW)
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {}
            })
            animator.addUpdateListener{
                //                每次改变基础的角度
                Log.d(SplashTAG,"${it.animatedValue as Float}")
                tRadius = outRadius*(it.animatedValue as Float)
                view.invalidate()
            }
            animator.start()
        }
        override fun draw(canvas: Canvas) {
            canvas.drawColor(Color.WHITE)
            canvas.save()
            canvas.translate(canvas.width/2f,canvas.height/2f)
            colorList.forEachIndexed{index,color->
                //                为小球设置颜色
                paint.color = color
//                获取小球圆心的偏移角度
                tAngle = (angle*index).toDouble()
//                计算小球圆心的X坐标值
                xTmp = (Math.cos(Math.toRadians(tAngle))*tRadius).toFloat()
//                计算小球圆心的Y坐标值
                yTmp = (Math.sin(Math.toRadians(tAngle))*tRadius).toFloat()
//                绘制小球
                canvas.drawCircle(xTmp,yTmp,radius,paint)
            }
            canvas.restore()
        }
    }

    /**
     * 展示状态 从中间扩散展示实际内容
     */
    private class ShowState(val view: StateView):State{
        private val paint = Paint()
        //        设定一个可变的半径
        private var tRadius = 0f
        private val animator = ValueAnimator.ofFloat(0f,1f)


        init {
            paint.style = Paint.Style.FILL_AND_STROKE
            paint.isAntiAlias = true
            paint.color = Color.WHITE

//            设置动画
            animator.duration = 1000
            animator.interpolator = LinearInterpolator()
            animator.addListener(object:Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    view.changeState(END)
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {}
            })
            animator.addUpdateListener{
                tRadius = it.animatedValue as Float
                view.invalidate()
            }
            animator.start()
        }
        override fun draw(canvas: Canvas) {
            val sc = canvas.saveLayer(0f,0f,canvas.width.toFloat(),canvas.height.toFloat(),paint)
            canvas.drawColor(Color.TRANSPARENT)
            canvas.drawRect(0f,0f,canvas.width.toFloat(),canvas.height.toFloat(),paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
            canvas.drawCircle(canvas.width/2f,canvas.height/2f,tRadius*canvas.height,paint)
            paint.xfermode = null
            canvas.restoreToCount(sc)
        }
    }
}