package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.SerializedName

data class StaffNet(
        @SerializedName("success")
        val success : Boolean,
        @SerializedName("data")
        val data : List<Staff>
)

data class Staff(
        @SerializedName("id") val id : Int,
        @SerializedName("api_id") val api_id : String?,
        @SerializedName("name") val name : String,
        @SerializedName("specialization") val specialization : String,
        @SerializedName("rating") val rating : Int,
        @SerializedName("show_rating") val show_rating : Int,
        @SerializedName("avatar") val avatar : String,
        @SerializedName("avatar_big") val avatar_big : String,
        @SerializedName("comments_count") val comments_count : Int,
        @SerializedName("votes_count") val votes_count : Int,
        @SerializedName("bookable") val bookable : Boolean,
        @SerializedName("information") val information : String,
        @SerializedName("position_id") val position_id : Int,
        @SerializedName("schedule_till") val schedule_till : String,
        @SerializedName("weight") val weight : Int,
        @SerializedName("fired") val fired : Int,
        @SerializedName("status") val status : Int,
        @SerializedName("hidden") val hidden : Int,
        @SerializedName("user") val user : String?,
        @SerializedName("prepaid") val prepaid : String,
        @SerializedName("position") val position : Position
)

data class Position(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title:String
)