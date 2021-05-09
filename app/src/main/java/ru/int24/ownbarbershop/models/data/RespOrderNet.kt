package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.SerializedName

data class RespOrderNet(
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("data")
    val data : List<OrderNet>?
)

data class OrderNet(
    @SerializedName("id") val id: Int,
    @SerializedName("record_id") val record_id: Int,
    @SerializedName("record_hash") val record_hash: String
)
