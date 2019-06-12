package com.example.msi_.customview.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.msi_.customview.R
import com.example.msi_.customview.adapter.OnlyOneTypeAdapter
import com.example.msi_.customview.base.BaseActivity
import com.example.msi_.customview.bean.RVBean
import com.example.msi_.customview.viewholder.SingleButtonHolder

class MainActivity : BaseActivity() {
    private lateinit var rvContent:RecyclerView
    private lateinit var adapter: OnlyOneTypeAdapter<RVBean>

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        rvContent = findViewById(R.id.rv_content)
    }

    override fun init() {
    }

    override fun initRv() {
        val list = ArrayList<RVBean>()
        list.add(RVBean("原始波浪展示",WaveActivity::class.java))
        list.add(RVBean("新版波浪展示",WaveActivity::class.java,1))
        list.add(RVBean("自动填充换行的layout",FlowLayoutActivity::class.java))
        list.add(RVBean("音量大小图",VoiceActivity::class.java))
        list.add(RVBean("跟随手指移动的Vuew",MoveActivity::class.java))
        list.add(RVBean("刷新",SwipeRefreshActivity::class.java))
        list.add(RVBean("滑块",SliderActivity::class.java))
        list.add(RVBean("声音波状图",VideoActivity::class.java))
        list.add(RVBean("PathMeasure",PathMeasureActivity::class.java))
        list.add(RVBean("SplashView",SplashActivity::class.java))
        list.add(RVBean("ParticlesView",ParticlesActivity::class.java))

        adapter = OnlyOneTypeAdapter(SingleButtonHolder::class.java)
        adapter.setList(list)
        rvContent.adapter = adapter
        rvContent.layoutManager = LinearLayoutManager(this)
    }

    override fun initToolsbar() {
        title = "自定义View类别"
    }

}
