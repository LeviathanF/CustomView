package com.example.msi_.customview.listener

/**
 *@Author：ZC
 *@Time： 2019/6/12 11:20
 *@Description：方便接收状态的接口
 **/
interface StateView{

    /**
     * 刷新当前View界面
     */
    fun invalidate()

    /**
     * 改变状态
     */
    fun changeState(state:Int)
}