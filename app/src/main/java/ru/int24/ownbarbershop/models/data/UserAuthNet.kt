package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.SerializedName

data class UserAuthNet(
        @SerializedName("success")
        val success : Boolean,
        @SerializedName("data")
        val data : UserAuth
)

data class UserAuth(
        @SerializedName("id") val id: String,
        @SerializedName("user_token") val user_token: String,
        @SerializedName("name") val name: String,
        @SerializedName("phone") val phone:String,
        @SerializedName("login") val login: String,
        @SerializedName("email") val email: String,
        @SerializedName("avatar")val avatar: String,
        @SerializedName("is_approved") val is_approved: Boolean
)

