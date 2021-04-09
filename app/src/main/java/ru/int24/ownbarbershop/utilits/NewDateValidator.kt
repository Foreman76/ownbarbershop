package ru.int24.ownbarbershop.utilits

import com.google.android.material.datepicker.CalendarConstraints
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
class NewDateValidator(private val listDateFromNet: List<String> ): CalendarConstraints.DateValidator {
    private val myCalendar = Calendar.getInstance(Locale.getDefault())
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd").apply { val timeZone = TimeZone.getTimeZone("UTC") }


    override fun isValid(date: Long): Boolean {
        val listDateFromNet = createMyRangeDate()
        val stringDate = dateFormat.format(date)
        myCalendar.time = dateFormat.parse(stringDate)
        return listDateFromNet.contains(myCalendar.timeInMillis)

    }

    private fun createMyRangeDate(): ArrayList<Long>{

        val dateToValidate: ArrayList<Long> = arrayListOf()

        listDateFromNet.forEach{dateString ->
            myCalendar.time = dateFormat.parse(dateString)
            dateToValidate.add(myCalendar.timeInMillis)}
        return dateToValidate
    }
}



