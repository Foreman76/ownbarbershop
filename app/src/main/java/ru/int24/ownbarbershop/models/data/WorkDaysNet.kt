package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.SerializedName

data class WorkDaysNet(

        @SerializedName("success")
        val success : Boolean,
        @SerializedName("data")
        val data : myData
)

data class myData(
        @SerializedName("booking_dates")
        val booking_dates : List<String>,
)

