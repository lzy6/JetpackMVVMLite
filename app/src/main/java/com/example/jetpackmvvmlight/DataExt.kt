package com.example.jetpackmvvmlight

import com.example.jetpackmvvmlight.entity.Main

fun dataMain() = ArrayList<Main>().apply {
    add(Main("简介", "关于JetpackMVVMLite\nJetpackMVVMLite有哪些是适合你"))
    add(Main("组件列表", "框架中使用到的技术组件"))
    add(Main("代码片段", "Kotlin片段\n请求API片段\nViewBinding"))
    add(Main("历史更新", "框架组件的选择与历史"))
}

