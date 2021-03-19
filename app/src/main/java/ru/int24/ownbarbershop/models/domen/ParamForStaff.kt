package ru.int24.ownbarbershop.models.domen

data class ParamForStaff(
        val companyid:Int,
        val datetime: String? = null,
        val service_ids:MutableList<String>? = null
)
