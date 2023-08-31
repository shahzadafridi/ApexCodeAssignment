package com.apex.codeassesment.util.error

import android.util.Log
import com.apex.codeassesment.R
import com.apex.codeassesment.util.DataState
import com.apex.codeassesment.util.resource.ResourceManager
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HttpExceptionHandler @Inject constructor(
    private val resourceManager: ResourceManager
) {
    suspend fun <T> wrap(block: suspend () -> T): DataState<T> {
        return try {
            val data = block.invoke()
            DataState.Success(data)
        } catch (e: Throwable) {
            DataState.Error(transformException(e))
        }
    }

    private fun transformException(exception: Throwable): BaseException {
        return when (exception) {
            is HttpException -> {
                val response = exception.response()!!

                val errorCode = response.code()
                response.errorBody()?.close()

                BaseException.httpError(errorCode, resourceManager.getString(R.string.undefined_error_message))
            }
            is IOException -> BaseException.networkError(resourceManager.getString(R.string.connection_error_message), exception)
            else -> BaseException.unexpectedError(exception)
        }
    }
}
