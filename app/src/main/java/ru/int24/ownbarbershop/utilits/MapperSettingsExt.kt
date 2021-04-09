package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.models.db.DBSettings
import ru.int24.ownbarbershop.models.domen.DomSettings

fun DBSettings.toDomModel(): DomSettings {
    return DomSettings(
            id = id,
            userEmail = userEmail,
            phone = phone,
            smsCode = smsCode,
            userName = userName,
            userToken = userToken,
            userPassword = userPassword
    )
}

fun DomSettings.toDBSettings(): DBSettings{
    return DBSettings(
            id = 0,
            userEmail = userEmail,
            phone = phone,
            smsCode = smsCode,
            userName = userName,
            userToken = userToken,
            userPassword = userPassword
    )
}