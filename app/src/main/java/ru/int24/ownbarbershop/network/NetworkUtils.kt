package ru.int24.ownbarbershop.network


import ru.int24.ownbarbershop.config.DefConfig

fun getHeaders(authUser: Boolean): Map<String, String> {

    val mapHeaders: Map<String, String>
    val authString:String
    when (authUser){
        true -> authString = "Bearer ${DefConfig.api}, User ${DefConfig.user_token}"
        else -> authString = "Bearer ${DefConfig.api}"
    }
    mapHeaders = mapOf("Content-type" to "application/json",
                       "Accept" to "application/vnd.yclients.v2+json",
                       "Authorization" to authString)
    return mapHeaders
}

