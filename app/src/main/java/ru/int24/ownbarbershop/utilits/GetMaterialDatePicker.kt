package ru.int24.ownbarbershop.utilits

import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker

class GetMaterialDatePicker() {

    companion object {

        fun getMaterialDatePicker(title:String, listWorkDays: List<String>): MaterialDatePicker<Long> {

           val newDateValidator = NewDateValidator(listWorkDays)

           return MaterialDatePicker.Builder.datePicker()
                    .setTitleText(title)
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .setCalendarConstraints(CalendarConstraints.Builder().setValidator(newDateValidator).build())
                    .build()
        }
    }


}