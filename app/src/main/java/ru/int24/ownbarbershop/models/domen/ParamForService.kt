package ru.int24.ownbarbershop.models.domen

data class ParamForService(
        val staff_id:Int? = null,
        val companyid:Int,
        val datetime: String? = null,
        val service_ids:String? = null
)
