package ru.int24.ownbarbershop.models.netresult

sealed class NetResult<out T> {

    data class Success<out T>(val value: T): NetResult<T>()
    data class GenericError(val code: Int? = null, val error: String? = null): NetResult<Nothing>()
    object NetworkError: NetResult<Nothing>()

}
