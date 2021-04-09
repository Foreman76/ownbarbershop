package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.SerializedName

data class SessionNet(

        @SerializedName("success")
        val success : Boolean,
        @SerializedName("data")
        val data : List<SessionTime>
)

data class SessionTime(
        @SerializedName("time") val time:String,
        @SerializedName("seance_length") val seance_length:Int,
        @SerializedName("sum_length") val sum_length:Int,
        @SerializedName("datetime") val datetime:String
)