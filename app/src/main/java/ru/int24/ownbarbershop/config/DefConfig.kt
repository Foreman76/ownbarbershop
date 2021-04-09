package ru.int24.ownbarbershop.config

import ru.int24.ownbarbershop.BuildConfig
import ru.int24.ownbarbershop.models.domen.DomSettings

object DefConfig {
    const val id: Int = BuildConfig.ID
    const val api: String = BuildConfig.API
    const val url: String = BuildConfig.SERVER_URL
    const val key_error: String = "ru.int24.ownbarbershop.routers.errormessage"
    var settings = DomSettings()

}


