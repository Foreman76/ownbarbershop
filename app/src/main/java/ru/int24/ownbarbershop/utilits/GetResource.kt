package ru.int24.ownbarbershop.utilits

import android.content.Context
import javax.inject.Inject

class GetResource @Inject constructor(private val AppContext: Context) {

    fun getString(resource: Int) = AppContext.getString(resource)

}