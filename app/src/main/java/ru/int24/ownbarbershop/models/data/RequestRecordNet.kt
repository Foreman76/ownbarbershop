package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.SerializedName

data class RequestRecordNet(

    @SerializedName("phone") val phone: String,
    @SerializedName("fullname") val fullname: String,
    @SerializedName("email") val email:String,
    @SerializedName("appointments") val appointments: ArrayList<OrderAppointments>,
    @SerializedName("code") val code:String,
    @SerializedName("notify_by_sms") val notify_by_sms:Int

)

data class OrderAppointments(

    @SerializedName("id") val id:Int,
    @SerializedName("services") val services: ArrayList<Int>,
    @SerializedName("staff_id") val staff_id: Int,
    @SerializedName("datetime") val datetime:String

)

