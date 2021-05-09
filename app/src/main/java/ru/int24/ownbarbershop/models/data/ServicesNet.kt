package ru.int24.ownbarbershop.models.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServicesNet(

        @SerializedName("success")
        val success : Boolean,

        @SerializedName("data")
        @Expose
        val data : DataNet,

//        @SerializedName("meta")
//        @Expose
//        val meta : Message
)
data class Message (
        @SerializedName("message")
        val message: String,
        )

data class DataNet (

        @SerializedName("services")
        val services : List<Services>,

        @SerializedName("category")
        val category : List<Category>
)

data class Services (

        @SerializedName("id")
        val id : Int,
        @SerializedName("title")
        val title : String,
        @SerializedName("category_id")
        val category_id : Int,
        @SerializedName("price_min")
        val price_min : Int,
        @SerializedName("price_max")
        val price_max : Int,
        @SerializedName("discount")
        val discount : Int,
        @SerializedName("comment")
        val comment : String,
        @SerializedName("weight")
        val weight : Int,
        @SerializedName("active")
        val active : Int,
        @SerializedName("sex")
        val sex : Int,
        @SerializedName("image")
        val image : String,
        @SerializedName("prepaid")
        val prepaid : String,
        @SerializedName("seance_length")
        val seance_length : Int
)

data class Category (

        @SerializedName("id")
        val id : Int,
        @SerializedName("title")
        val title : String,
        @SerializedName("sex")
        val sex : Int,
        @SerializedName("api_id")
        val api_id : Int,
        @SerializedName("weight")
        val weight : Int
)