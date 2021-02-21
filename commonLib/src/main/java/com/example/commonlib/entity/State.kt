package com.example.commonlib.entity

class State {
    //状态码
    var stateCode: Int=0

    //stateCode为COMPLETE可调用
    var isRefresh = false

    //stateCode为ERROR可调用
    var errorCode: Int=0

    //stateCode为HAS_MORE可调用
    lateinit var page: Page<*>

    //stateCode为EMPTYVIEW可调用
    var stateLayout: Int=0
}