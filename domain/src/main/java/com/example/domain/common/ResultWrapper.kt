package com.example.domain.common


import java.lang.Exception

sealed class ResultWrapper<out T> {
    data class ServerError(val error : com.example.domain.exceptions.ServerError) : ResultWrapper<Nothing>()
    data class Error(val error : Exception) : ResultWrapper<Nothing>()
    data class Success<T>(val data : T) : ResultWrapper<T>()
    data object  Loading : ResultWrapper<Nothing>()
}