package com.usecases


import com.repo.UserRepoImpl
import com.response.UserResponse
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val userRepo: UserRepoImpl
) : UserUseCase {
    override fun getUser(page: Int?, results: Int?, seed: String?)
            : Single<UserResponse> {
//        val a=10
        return userRepo.getUser(page, results, seed)
    }

}