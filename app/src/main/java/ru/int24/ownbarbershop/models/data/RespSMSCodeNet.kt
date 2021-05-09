package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RespSMSCodeNet(
        @SerializedName("success")
        val success : Boolean,

        @SerializedName("data")
        @Expose
        val data : String?,
)
