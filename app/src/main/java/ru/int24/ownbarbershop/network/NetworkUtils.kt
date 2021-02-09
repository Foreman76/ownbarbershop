package ru.int24.ownbarbershop.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.nerwork.ServicesNet
import ru.int24.ownbarbershop.models.netresult.NetResult
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend() -> T): NetResult<T>{

    return withContext(Dispatchers.IO){
        try {
            NetResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> NetResult.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorString = "Network error"
                    NetResult.GenericError(code, errorString)
                }
                else -> NetResult.GenericError(null, "Network error")
            }
        }
    }

}

fun getHeaders(authUser: Boolean): Map<String, String> {

    val mapHeaders: Map<String, String>
    var authString = ""
    when (authUser){
        true -> authString = "Bearer ${DefConfig.api}, User ${DefConfig.user_token}"
        else -> authString = "Bearer ${DefConfig.api}"
    }
    mapHeaders = mapOf("Content-type" to "application/json",
                       "Accept" to "application/vnd.yclients.v2+json",
                       "Authorization" to authString)
    return mapHeaders
}

//mapper
fun ServicesNet.toDomModel(){
    val a=0
}

