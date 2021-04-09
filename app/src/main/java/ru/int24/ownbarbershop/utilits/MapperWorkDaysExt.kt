package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.models.data.WorkDaysNet

fun WorkDaysNet.toDomModel():List<String>{
    val listBookingDates = mutableListOf<String>()
    this.data.booking_dates.forEach{ stringDate ->
        listBookingDates.add(stringDate)
    }
    return listBookingDates

}