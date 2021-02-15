package ru.int24.ownbarbershop.utilits

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class NetworkUtility {
    companion object {
        private const val TAG = "NetworkUtility"
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
    }


    suspend inline fun <T> safeApiCall(emitter: RemoteErrorEmitter, crossinline callFunction: suspend () -> T): T? {
        return try{
            val myObject = withContext(Dispatchers.IO){ callFunction.invoke() }
            myObject
        }catch (e: Exception){
            withContext(Dispatchers.Main){
                e.printStackTrace()
                Log.e("BaseRemoteRepo", "Call error: ${e.localizedMessage}", e.cause)
                when(e){
                    is HttpException -> {
                        if(e.code() == 401) emitter.onError(ErrorType.Session_Expired())
                        else {
                            val body = e.response()?.errorBody()
                            emitter.onError(getErrorMessage(body))
                        }
                    }
                    is SocketTimeoutException -> emitter.onError(ErrorType.Timeout())
                    is IOException -> emitter.onError(ErrorType.Network())
                    else -> emitter.onError(ErrorType.Unknown())
                }
            }
            null
        }
    }

    fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
                else -> "Something wrong happened"
            }
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }
}