package com.extensions

import com.error.AppError

fun Throwable.toApiFailure(): AppError {
    return AppError.ApiFailure(this)
}