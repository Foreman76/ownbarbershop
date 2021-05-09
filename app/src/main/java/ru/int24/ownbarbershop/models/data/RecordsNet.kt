package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.SerializedName

data class RecordsNet(
        @SerializedName("id") val id: Int,
        @SerializedName("services") val services: ArrayList<RecordServices>,
        @SerializedName("company") val company: RecordCompany,
        @SerializedName("staff") val staff: RecordStaff,
        @SerializedName("date") val date :String,
        @SerializedName("datetime") val datetime:String,
        @SerializedName("deleted") val deleted:Boolean,
        @SerializedName("currency_short_title") val currency_short_title:String
)


data class RecordServices(
        @SerializedName("id") val id:Int,
        @SerializedName("title") val title: String,
        @SerializedName("cost") val cost: Float
)

data class RecordCompany(
        @SerializedName("id") val id:Int,
        @SerializedName("title") val title: String,
        @SerializedName("city") val city:String,
        @SerializedName("timezone") val timezone:String,
        @SerializedName("address") val address:String,
        @SerializedName("phone") val phone:String,
        @SerializedName("coordinate_lat") val coordinate_lat:Float,
        @SerializedName("coordinate_lng") val coordinate_lng:Float,
        @SerializedName("site") val site:String
)

data class RecordStaff(
        @SerializedName("id") val id:Int,
        @SerializedName("name") val name:String,
        @SerializedName("specialization") val specialization:String
)