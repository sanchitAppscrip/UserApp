package com.error

sealed class AppError {
    data class ApiError(val statusCode: Int, val message: String) : AppError()
    data class ApiFailure(val throwable: Throwable) : AppError()
    data class GeneralError(val throwable: Throwable) : AppError()
    data class GetFCMTokenFailure(val throwable: Throwable?) : AppError()
    data class OrderCancelled(val message: String) : AppError()
    object UserDataNotFound : AppError()
    object DeliveryAddressNotFound : AppError()
    object ErrorProcessingImage : AppError()
}