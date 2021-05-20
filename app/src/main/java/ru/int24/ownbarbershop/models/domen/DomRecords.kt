package ru.int24.ownbarbershop.models.domen

data class DomRecords(
        val id: Int,
        val services: ArrayList<DomRecordServices>,
        val company: DomRecordCompany,
        val staff: DomRecordStaff,
        val date :String,
        val datetime:String,
        val deleted:Boolean,
)

data class DomRecordServices(
        val id:Int,
        val title: String,
        val cost: Float
)

data class DomRecordCompany(
        val id:Int,
        val title: String,
        val city:String,
        val timezone:String,
        val address:String,
        val phone:String,
        val coordinate_lat:Float,
        val coordinate_lng:Float,
        val site:String,
        val currency_short_title:String
)

data class DomRecordStaff(
        val id:Int,
        val name:String,
        val specialization:String
)