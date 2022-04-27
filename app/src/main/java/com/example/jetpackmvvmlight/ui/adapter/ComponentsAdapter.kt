package com.example.jetpackmvvmlight.ui.adapter

import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.commonlib.BindingViewHolder
import com.example.commonlib.newBindingViewHolder
import com.example.commonlib.onClick
import com.example.jetpackmvvmlight.databinding.ItemComponentsBinding

class ComponentsAdapter :BaseQuickAdapter<String,BindingViewHolder<ItemComponentsBinding>>(0) {

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int) =
        newBindingViewHolder<ItemComponentsBinding>(parent)

    override fun convert(holder: BindingViewHolder<ItemComponentsBinding>, item: String) {
        holder.binding.run {
            tvContent.text=item
        }
    }
}