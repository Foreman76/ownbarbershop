package ru.int24.ownbarbershop.models.domen

data class DomSubscription(

       val id:Int,
       val number:String,
       val balance_string: String,
       val is_frozen:Boolean,
       val freeze_period:Int,
       val period:Int,
       val period_unit_id:Int,
       val expiration_date:String

)
