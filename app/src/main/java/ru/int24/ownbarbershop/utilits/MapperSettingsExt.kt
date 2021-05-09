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
            userPassword = userPassword,
            avatar = avatar,
            user_id = user_id
    )
}

fun DomSettings.toDBSettings(): DBSettings{
    return DBSettings(
            id = id,
            userEmail = userEmail,
            phone = phone,
            smsCode = smsCode,
            userName = userName,
            userToken = userToken,
            userPassword = userPassword,
            avatar = avatar,
            user_id = user_id
    )
}