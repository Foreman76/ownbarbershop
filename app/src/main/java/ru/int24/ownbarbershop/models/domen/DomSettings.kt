package ru.int24.ownbarbershop.models.domen

data class DomSettings(
        var id:Int = 0,
        val phone: String = "",
        var smsCode: String = "",
        var userName: String = "",
        var userToken: String = "",
        var userEmail: String = "",
        var userPassword: String = "",
        var avatar:String = "",
        var user_id:String = ""
)
