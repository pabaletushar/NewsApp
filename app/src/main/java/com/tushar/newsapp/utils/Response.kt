package com.tushar.newsapp.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

data class Response<out T>(
    val status: Status,
    val data: T? = null,
    val error: Throwable? = null
) {
    enum class Status { LOADING, SUCCESS, ERROR }

    companion object {
        fun <T> loading(): Response<T> = Response(Status.LOADING, null, null)
        fun <T> success(data: T? = null): Response<T> = Response(Status.SUCCESS, data, null)
        fun <T> error(error: Throwable): Response<T> = Response(Status.ERROR, null, error)
    }
}
data class FlowResponse<out T>(
    val status: Status,
    val data: T? = null,
    val error: String? = null
) {
    enum class Status { LOADING, SUCCESS, ERROR }

    companion object {
        fun <T> loading(): FlowResponse<T> = FlowResponse(Status.LOADING, null, null)
        fun <T> success(data: T? = null): FlowResponse<T> = FlowResponse(Status.SUCCESS, data, null)
        fun <T> error(error: String): FlowResponse<T> = FlowResponse(Status.ERROR, null, error)
    }
}



sealed class FlowResult<out T> {

    data class Success<out T>(val data: T) : FlowResult<T>()
    data class Failure(val msg: String?,val code:Int?) : FlowResult<Nothing>()
    object Loading : FlowResult<Nothing>()
}

fun <T> Flow<FlowResult<T>>.doOnSuccess(action: suspend (T) -> Unit): Flow<FlowResult<T>> =
    transform { result ->
        if (result is FlowResult.Success) {
            action(result.data)
        }
        return@transform emit(result)
    }

fun <T> Flow<FlowResult<T>>.doOnFailure(action: suspend (String?) -> Unit): Flow<FlowResult<T>> =
    transform { result ->
        if (result is FlowResult.Failure) {
            action(result.msg)
        }
        return@transform emit(result)
    }

fun <T> Flow<FlowResult<T>>.doOnLoading(action: suspend () -> Unit): Flow<FlowResult<T>> =
    transform { result ->
        if (result is FlowResult.Loading) {
            action()
        }
        return@transform emit(result)
    }

