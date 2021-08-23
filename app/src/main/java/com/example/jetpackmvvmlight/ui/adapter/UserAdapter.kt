package com.example.jetpackmvvmlight.ui.adapter

import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.commonlib.BindingViewHolder
import com.example.commonlib.newBindingViewHolder
import com.example.jetpackmvvmlight.R
import com.example.jetpackmvvmlight.databinding.ItemUserBinding
import com.example.jetpackmvvmlight.entity.PageUser

class UserAdapter :BaseQuickAdapter<PageUser,BindingViewHolder<ItemUserBinding>>(0) {

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int) =
        newBindingViewHolder<ItemUserBinding>(parent)

    override fun convert(holder: BindingViewHolder<ItemUserBinding>, item: PageUser) {

    }
}