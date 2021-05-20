package ru.int24.ownbarbershop.utilits

import java.text.SimpleDateFormat
import java.util.*

class GetDataFormat {

    companion object {
        fun getUserFormatStringDateFromDate(pattern: String, date: Long): String{
            return getOutputDateFormate(pattern).format(date)
        }

        fun getDateBaseISOFormat(date:Long): String {
            return getOutputDateFormate("yyyy-MM-dd'T'HH:mm:ssZ").format(date)
        }

        fun getUserFormatStringDateFromStringDate(patternIn: String, patternOut:String, stringDate: String): String {
            val date = getOutputDateFormate(patternIn).parse(stringDate)
            return getOutputDateFormate(patternOut).format(date!!)
        }

        private fun getOutputDateFormate(pattern: String): SimpleDateFormat {
            return  SimpleDateFormat(pattern, Locale.getDefault()).apply { val timeZone = TimeZone.getTimeZone("UTC")}
        }

        fun getCurrentOffset(): String {
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault())
            val currentLocalTime = calendar.getTime()
            val dateFormat = SimpleDateFormat("Z");
            val offset = dateFormat.format(currentLocalTime)
            return offset.removePrefix("+")
        }

        fun getDateFromString(datetime:String):Date? {
            val sdf = getOutputDateFormate("yyyy-MM-dd'T'HH:mm:ssZ")
            return sdf.parse(datetime)
        }
    }



}