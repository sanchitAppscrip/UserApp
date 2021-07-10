package com.ui

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

    }

    override fun getLayout(): Int = R.layout.item_user

    fun getUserDetail(): UserDto? = userDto
}

//class SongItem : BindableItem<SongBinding> {
//    constructor(song: Song?) : this(song) {}
//
//    override fun bind(binding: SongBinding, position: Int) {
//        binding.setSong(song)
//    }
//
//    override fun getLayout(): Int {
//        return R.layout.song
//    }
//}