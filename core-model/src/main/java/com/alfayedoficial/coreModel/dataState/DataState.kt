package com.alfayedoficial.coreModel.dataState

import okhttp3.ResponseBody

sealed interface DataState<T : Any> {
    data class ApiSuccess<T : Any>(val data: T, val message: String? = null, val statusCode: Int? = null, val success: Boolean? = null) :
        DataState<T>
    data class ApiError<T : Any>(val exception: Failure, val statusCode: Int? = null) :
        DataState<T>
    data class IsLoading<T : Any>(val data: T? = null , val status : Boolean? =null) : DataState<T>
}

sealed class Failure {

    data class NetworkConnection(var code: Int? = null, var message: String? = null , var errorBody: ResponseBody? = null) : Failure()
    sealed class ServerError : Failure() {
        data class NotFound(var code: Int? = null, var message: String? = null, var errorBody: ResponseBody? = null ,var isExist:Boolean? = null) : ServerError()
        data class BadRequest(var code: Int? = null, var message: String? = null, var errorBody: ResponseBody? = null ,var isExist:Boolean? = null) : ServerError()
        data class Unauthorized(var code: Int? = null, var message: String? = null, var errorBody: ResponseBody? = null,  var isExist:Boolean? = null) : ServerError()
        data class Forbidden(var code: Int? = null, var message: String? = null, var errorBody: ResponseBody? = null ,var isExist:Boolean? = null) : ServerError()
        data class MethodNotAllowed(var code: Int? = null, var message: String? = null, var errorBody: ResponseBody? = null ,var isExist:Boolean? = null) : ServerError()
        data class UnProcessableEntity(var code: Int? = null, var message: String? = null, var errorBody: ResponseBody? = null ,var isExist:Boolean? = null) : ServerError()
        data class InternalServerError(var code: Int? = null, var message: String? = null, var errorBody: ResponseBody? = null, var isExist:Boolean? = null) : ServerError()
        data class Unknown(var code: Int? = null, var message: String? = null, var errorBody: ResponseBody? = null ,var isExist:Boolean? = null) : ServerError()
    }

    /** * Extend this class for feature specific failures.*/
    data class ThrowableFailure(var code: Int? = null, var message: String? = null) : Failure()
}
