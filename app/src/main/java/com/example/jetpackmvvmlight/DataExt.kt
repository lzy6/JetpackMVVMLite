package com.example.jetpackmvvmlight

import com.example.jetpackmvvmlight.entity.Main

fun dataMain() = ArrayList<Main>().apply {
    add(Main("简介", "· 关于JetpackMVVMLite\n· JetpackMVVMLite有哪些是适合你"))
    add(Main("组件列表", "· 框架中使用到的技术组件"))
    add(Main("代码片段", "· Kotlin片段\n· 请求API片段\n· ViewBinding"))
    add(Main("历史更新", "· 框架组件的选择与历史"))
}

fun dataComponents()=ArrayList<String>().apply {
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

