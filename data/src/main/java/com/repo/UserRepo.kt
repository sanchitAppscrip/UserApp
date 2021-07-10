package com.repo

import com.response.ApiResponse
import com.response.UserResponse
import io.reactivex.rxjava3.core.Single

interface UserRepo {

    //    fun changeGameState(position: Int, player: Player): Single<String>
//    fun resetGame()
    fun getUser(): Single<ApiResponse>

}