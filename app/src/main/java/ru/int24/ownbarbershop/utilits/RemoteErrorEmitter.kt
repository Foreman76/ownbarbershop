package ru.int24.ownbarbershop.utilits


interface RemoteErrorEmitter {
    fun onError(msg: String)
    fun onError(errorType: ErrorType)
}