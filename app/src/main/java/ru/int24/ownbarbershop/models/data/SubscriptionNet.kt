package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubscriptionNet(

        @SerializedName("success")
        val success : Boolean,

        @SerializedName("data")
        @Expose
        val data : List<SubNet>,

        @SerializedName("meta")
        val meta : SubMeta

)

data class SubMeta(
        @SerializedName("count") val count:Int
)

data class SubNet(

        @SerializedName("id") val id:Int,
        @SerializedName("number") val number:String,
        @SerializedName("is_frozen") val is_frozen:Boolean,
        @SerializedName("balance_string") val balance_string: String,
        @SerializedName("freeze_period") val freeze_period:Int,
        @SerializedName("period") val period:Int,
        @SerializedName("period_unit_id") val period_unit_id:Int,
        @SerializedName("expiration_date") val expiration_date:String
)