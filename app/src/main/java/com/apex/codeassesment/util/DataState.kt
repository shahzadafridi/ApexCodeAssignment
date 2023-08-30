package com.apex.codeassesment.util

import com.apex.codeassesment.util.error.BaseException

sealed class DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val exception: BaseException) : DataState<Nothing>()
}