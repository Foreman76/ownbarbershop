package ru.int24.ownbarbershop.models.domen

data class DomSettings(
        val id:Int = 0,
        val phone: String = "",
        val smsCode: String = "",
        val userName: String = "",
        val userToken: String = "",
        val userEmail: String = "",
        val userPassword: String = ""
)
