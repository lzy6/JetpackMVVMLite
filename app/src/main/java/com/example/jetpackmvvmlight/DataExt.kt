package com.example.jetpackmvvmlight

import android.content.Context
import com.example.jetpackmvvmlight.entity.Describe

/**
 * 首页数据
 */
fun dataMain() = ArrayList<Describe>().apply {
    add(
        Describe(
            "简介",
            "· 关于JetpackMVVMLite\n· JetpackMVVMLite有哪些是适合你"
        )
    )
    add(Describe("组件列表", "· 框架中使用到的技术组件"))
    add(
        Describe(
            "代码片段",
            "· Kotlin片段\n· 请求API片段\n· ViewBinding"
        )
    )
    add(
        Describe(
            "历史组件",
            "· 为什么使用ViewBinding\n· 为什么使用coil\n· 为什么使用LiveEventBus\n· 更多"
        )
    )
}

/**
 * 简介数据
 */
fun Context.dataBriefIntroduction() = ArrayList<Describe>().apply {
    add(
        Describe(
            "关于JetpackMVVMLite",
            getString(R.string.brief_introduction)
        )
    )
    add(
        Describe(
            "JetpackMVVMLite有哪些是适合你",
            getString(R.string.features)
        )
    )
}

/**
 * 组件列表数据
 */
fun dataComponents() = ArrayList<String>().apply {
    add("viewpager2")
    add("activity-ktx")
    add("fragment-ktx")
    add("constraintlayout")
    add("material")
    add("lifecycle")
    add("retrofit2")
    add("coil")
    add("BaseRecyclerViewAdapterHelper")
    add("permissionsdispatcher-ktx")
    add("autosize")
    add("mmkv")
    add("filedownloader")
    add("shimmerlayout")
    add("live-event-bus")
    add("recycler-view-divider")
    add("smart:refresh")
    add("disklrucache")
    add("arouter")
}

/**
 * 历史组件数据
 */
fun Context.dataHistoryComponents() = ArrayList<Describe>().apply {
    add(
        Describe(
            "为什么使用ViewBinding",
            getString(R.string.why_use_viewbinding)
        )
    )
    add(
        Describe(
            "为什么使用coil",
            getString(R.string.why_use_load_image)
        )
    )
    add(
        Describe(
            "为什么使用LiveEventBus",
            getString(R.string.why_use_event)
        )
    )
}

fun Context.dataCodeSegment() = ArrayList<Describe>().apply {
    add(Describe("LiveEventBus", getString(R.string.code_event), true))
    add(Describe("网络请求代码", getString(R.string.code_net_request), true))
    add(Describe("Activity/Fragment监听接口回调", getString(R.string.code_api_result), true))
    add(Describe("ViewBinding-Activity", getString(R.string.code_activity_viewbinding), true))
    add(Describe("ViewBinding-Fragment", getString(R.string.code_fragment_viewbinding), true))
    add(Describe("拓展方法-重写某个接口发现要重写所有但却只需要一两个?", getString(R.string.code_custom_method_override_method), true))
    add(Describe("点击事件", getString(R.string.code_custom_click), true))
    add(Describe("TextView删除线", getString(R.string.code_custom_del_line), true))
    add(Describe("RecycleView骨骼加载", getString(R.string.code_custom_skeleton), true))
    add(Describe("下载文件", getString(R.string.code_custom_download), true))
    add(Describe("协议", getString(R.string.code_custom_protocol), true))
}

