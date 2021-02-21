package com.example.commonlib.entity

import kotlin.collections.ArrayList

class Page<T> {
    lateinit var list: ArrayList<T>
    var current_page = 0
    var from = 0
    var last_page = 0
    var per_ = 0
    var to = 0
    var total = 0
}