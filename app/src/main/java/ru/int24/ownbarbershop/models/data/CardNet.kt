package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.SerializedName

data class CardNet(
        @SerializedName("id") val id: Int,
        @SerializedName("balance") val balance: Double,
        @SerializedName("paid_amount") val paid_amount:Double,
        @SerializedName("sold_amount") val sold_amount:Double,
        @SerializedName("visits_count") val visits_count: Int,
        @SerializedName("number") val number:String,
        @SerializedName("salon_group_id") val salon_group_id:Int,
        @SerializedName("type") val type: TypeCard,
        @SerializedName("salon_group") val salon_group: TypeSalon,
        @SerializedName("programs") val programs: List<ProgramsSalon>,
        @SerializedName("rules") val rules: List<RulesSalon>
)

data class TypeCard(
        @SerializedName("id") val id:Int,
        @SerializedName("title") val title:String
)

data class TypeSalon(
        @SerializedName("id") val id:Int,
        @SerializedName("title") val title:String
)

data class ProgramsSalon(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title:String,
        @SerializedName("loyalty_type_id") val loyalty_type_id:Int,
        @SerializedName("item_type_id") val item_type_id:Int,
        @SerializedName("value_unit_id") val value_unit_id:Int,
        @SerializedName("group_id") val group_id:Int,
        @SerializedName("loyalty_type") val loyalty_type: LoyaltyType
)

data class RulesSalon(
        @SerializedName("id") val id:Int,
        @SerializedName("loyalty_program_id") val loyalty_program_id:Int,
        @SerializedName("loyalty_type_id") val loyalty_type_id:Int,
        @SerializedName("value") val value:Double
)

data class LoyaltyType(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title:String,
        @SerializedName("is_discount") val is_discount : Boolean,
        @SerializedName("is_cashback") val is_cashback: Boolean,
        @SerializedName("is_static") val is_static: Boolean,
        @SerializedName("is_accumulative") val is_accumulative: Boolean
)