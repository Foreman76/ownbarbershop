package ru.int24.ownbarbershop.models.domen

data class ParamForWorkDays(
        val staff_id:Int = 0,
        val companyid:Int,
        val service_ids:MutableList<String>? = null
)
