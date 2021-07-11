package com.ui

import com.test.userapp.R
import com.test.userapp.databinding.ItemLoadingBinding
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.GroupieViewHolder


class LoadingItem : BindableItem<ItemLoadingBinding>() {


    override fun getLayout(): Int = R.layout.item_loading
    override fun bind(viewBinding: ItemLoadingBinding, position: Int) {

    }
}