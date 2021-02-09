package ru.int24.ownbarbershop.models.domen

sealed class DomResult<out T>{
    data class Success<out T>(val value: T): DomResult<Nothing>()
    data class Error(val error: String? = null): DomResult<Nothing>()
}
