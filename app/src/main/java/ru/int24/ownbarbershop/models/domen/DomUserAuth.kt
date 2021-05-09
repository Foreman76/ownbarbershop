package ru.int24.ownbarbershop.models.domen

data class DomUserAuth(
        val id: String,
        val user_token: String,
        val name: String,
        val phone:String,
        val login: String,
        val email: String,
        val avatar: String,
        val is_approved: Boolean,
        val user_id:String
)
