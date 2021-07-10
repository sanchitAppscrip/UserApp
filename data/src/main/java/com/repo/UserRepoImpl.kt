package com.repo

import android.util.Log
import com.remote.UserApi
import com.response.ApiResponse
import com.response.UserResponse
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    val userApi: UserApi
) : UserRepo {
//    override fun changeGameState(position: Int, player: Player): Single<String> {
//        var msg =
//            if (gameHandler.game.hasGameEnded()) {
//                "GameOver"
//            } else {
//                gameHandler.game.changeGameState(position, player)
//            }
//
//        return Single.just(msg)
//    }
//
//    override fun resetGame() {
//        gameHandler.game.reset()
//    }

    override fun getUser(): Single<ApiResponse> {
        Log.d("Repo", " Call initiate")
//            val response =  userApi.getUser()

//        Log.d("Repo"," Call completed")

        return userApi.getUser()

//        return Single.create<UserResponse>{
//            userApi.getUser()
//        }.subscribeOn(Schedulers.io())
    }
}