package com.platform.mediacenter.data.remote

sealed class NetworkResult <T>(val data : T? = null ,val message : String? = null) {

    class Success<T>(data: T? , responseCode : String) : NetworkResult<T>(data , responseCode)
    class Error<T>(data: T? = null , message: String?) : NetworkResult<T>(data,message)
    class Loading<T> : NetworkResult<T>()
}
