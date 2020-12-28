package com.example.jetpackmvvmlight.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackmvvmlight.R
import com.example.jetpackmvvmlight.entity.PageUser

class UserAdapter :BaseQuickAdapter<PageUser,BaseViewHolder>(R.layout.item_user) {
    override fun convert(holder: BaseViewHolder, item: PageUser) {

    }
}