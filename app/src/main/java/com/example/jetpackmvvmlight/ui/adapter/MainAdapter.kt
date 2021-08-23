package com.example.jetpackmvvmlight.ui.adapter

import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.commonlib.BindingViewHolder
import com.example.commonlib.newBindingViewHolder
import com.example.jetpackmvvmlight.R
import com.example.jetpackmvvmlight.databinding.ItemMainBinding

class MainAdapter :BaseQuickAdapter<String,BindingViewHolder<ItemMainBinding>>(0) {

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int) =
        newBindingViewHolder<ItemMainBinding>(parent)

    override fun convert(holder: BindingViewHolder<ItemMainBinding>, item: String) {

    }

}