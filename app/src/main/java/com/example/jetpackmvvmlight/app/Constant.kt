package com.example.jetpackmvvmlight.app

import com.example.jetpackmvvmlight.R

object Constant{
    /**
     * 状态
     */
    const val BEFORE = 1//加载之前
    const val COMPLETE = 2//加载(成功/失败)完成
    const val ERROR = 3//加载出错
    const val HAS_MORE = 4//是否有更多数据
    const val STATE_LAYOUT = 5//设置空数据页面
    const val REQUEST_SUCCESS = 200//请求成功
    const val VIEW_EMPTY = R.layout.view_empty//空页面

    /**
     * 储存信息/习惯
     */
    const val TOKEN = "token"//token
}