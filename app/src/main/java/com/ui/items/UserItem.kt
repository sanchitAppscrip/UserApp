package com.ui.items

import com.bumptech.glide.Glide
import com.response.UserDto
import com.test.userapp.R
import com.test.userapp.databinding.ItemUserBinding
import com.xwray.groupie.databinding.BindableItem


class UserItem() : BindableItem<ItemUserBinding>() {
    var userDto: UserDto? = null
    lateinit var callback: Callback

    constructor(userDto: UserDto? = null, callback: Callback) : this() {
        this.userDto = userDto
        this.callback = callback
    }

    override fun bind(viewBinding: ItemUserBinding, position: Int) {
        viewBinding.user = userDto
        Glide.with(viewBinding.ivProfile)
            .load(userDto?.image?.large)
            .placeholder(R.color.color_divider)
            .into(viewBinding.ivProfile)

        viewBinding.rlUser.setOnClickListener {
            userDto?.let { it1 -> callback.onUserItemClickListener(it1) }
        }

    }

    override fun getLayout(): Int = R.layout.item_user

    interface Callback {
        fun onUserItemClickListener(userDto: UserDto)
    }

}
