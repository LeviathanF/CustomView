package com.example.msi_.customview.bean

/**
 *@Author：ZC
 *@Time： 2019/6/12 17:14
 *@Description：粒子球实体类
 **/
data class ParticleBean(val x:Float,val y:Float,val radius:Float = 2f,var xSpeed:Int,
var ySpeed:Int,var xAccSpeed:Int,var yAccSpeed:Int)