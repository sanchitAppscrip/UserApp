package com.ui

import com.bumptech.glide.Glide
import com.response.UserDto
import com.test.userapp.R
import com.test.userapp.databinding.ItemUserBinding
import com.xwray.groupie.databinding.BindableItem


class UserItem() : BindableItem<ItemUserBinding>() {
    var userDto: UserDto? = null

    constructor(userDto: UserDto? = null) : this() {
        this.userDto = userDto
    }

    override fun bind(viewBinding: ItemUserBinding, position: Int) {
        viewBinding.user = userDto
        Glide.with(viewBinding.ivProfile)
            .load(userDto?.image?.large)
            .placeholder(R.color.color_divider)
            .into(viewBinding.ivProfile)
    }

    override fun getLayout(): Int = R.layout.item_user

}
