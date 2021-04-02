package ru.int24.ownbarbershop.UiInterface

import android.widget.ImageView

interface ArrowBack {
    fun hideShowArrowBack(hideArrow:Boolean)
    fun comeBackInstanceImageView(): ImageView
    fun handlerOnClick(function: () -> Unit)
}