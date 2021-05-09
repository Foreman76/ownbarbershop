package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.SerializedName

data class RequestSMSCodeNet(
        @SerializedName("phone")
        val phone: String,
        @SerializedName("fullname")
        val fullname: String
)
