package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.models.data.UserAuthNet
import ru.int24.ownbarbershop.models.domen.DomUserAuth

fun UserAuthNet.toDomModel(): DomUserAuth{
    return DomUserAuth(
            id = this.data.id,
            user_token = this.data.user_token,
            name = this.data.name,
            phone = this.data.phone,
            login = this.data.login,
            email = this.data.email,
            avatar = this.data.avatar,
            is_approved = this.data.is_approved,
            user_id = this.data.id
    )
}