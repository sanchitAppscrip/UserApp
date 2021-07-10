package com.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.error.AppError
import com.model.Resource
import com.model.Response
import com.remote.UserApi
import com.repo.UserRepoImpl
import com.response.ApiResponse
import com.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepo: UserRepoImpl,
    val userApi: UserApi
) : ViewModel() {
    private val disposables = CompositeDisposable()

    val userObserver: MutableLiveData<Resource<UserResponse>> =
        MutableLiveData<Resource<UserResponse>>()

    fun getUsersObserver(): LiveData<Resource<UserResponse>> = userObserver


//    fun init() {
//        cells = ObservableArrayMap()
//        currentPlayer = player1
//        response.value = Response.success("Player1 turn")
//    }

    fun getUsers() {
        disposables.add(userApi.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({ gameStatus ->
                Log.d("Viewmodel", "Response Success")
                Log.d("Viewmodel", " value ${gameStatus.json}")
                Log.d("Viewmodel", " value ${gameStatus?.json?.results?.size}")
                userObserver.value = Resource.success(gameStatus.json)
            }) { throwable ->

                Log.d("Viewmodel", "Response Error")
            })
    }

//    fun onClickedCellAt(row: Int, column: Int, cellNo: Int) {
//
//            disposables.add(gameRepo.changeGameState(cellNo, currentPlayer)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe { response.setValue(Response.loading()) }
//                .subscribe(
//                    { gameStatus ->
//                        response.value = Response.success(gameStatus)
//                        if (!gameStatus.equals("GameOver"))
//                            cells?.put(stringFromNumbers(row, column), currentPlayer.value)
//                    }
//                ) { throwable -> response.setValue(Response.error(throwable)) }
//            )
//        }
//    }

//    fun reset() {
//        cells?.forEach { (key, _) ->
//            cells?.put(key, "")
//        }
//        init()
//        gameReset = true
//        disposables.clear()
//        gameRepo.resetGame()
//    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}