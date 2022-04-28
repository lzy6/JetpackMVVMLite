package com.example.jetpackmvvmlight.ui.adapter

import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.commonlib.BindingViewHolder
import com.example.commonlib.newBindingViewHolder
import com.example.jetpackmvvmlight.databinding.ItemDescribeBinding
import com.example.jetpackmvvmlight.entity.Describe
import io.noties.markwon.Markwon

class DescribeAdapter : BaseQuickAdapter<Describe, BindingViewHolder<ItemDescribeBinding>>(0) {

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int) =
        newBindingViewHolder<ItemDescribeBinding>(parent)

    override fun convert(holder: BindingViewHolder<ItemDescribeBinding>, item: Describe) {
        holder.binding.run {
            tvTitle.text = item.title
            if (item.isMarkdown) {
                Markwon.builder(context).build().setMarkdown(tvContent, item.content)
            } else {
                tvContent.text = item.content
            }
        }
    }
}