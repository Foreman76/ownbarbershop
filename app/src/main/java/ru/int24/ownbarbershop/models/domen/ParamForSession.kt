package ru.int24.ownbarbershop.models.domen

data class ParamForSession(

        val staff_id:Int = 0,
        val companyid:Int,
        val datetime: String = "",
        val service_ids:MutableList<String>? = null

)
