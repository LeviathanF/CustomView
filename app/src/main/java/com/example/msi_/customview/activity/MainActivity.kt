package com.example.msi_.customview.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
        adapter = OnlyOneTypeAdapter(SingleButtonHolder::class.java)
        adapter.setList(list)
        rvContent.adapter = adapter
        rvContent.layoutManager = LinearLayoutManager(this)
    }

    override fun initToolsbar() {
        title = "自定义View类别"
    }

}