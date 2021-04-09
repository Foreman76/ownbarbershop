package ru.int24.ownbarbershop.UiInterface

interface InterfaceArrowBack {
    fun hideShowArrowBack(hideArrow:Boolean)
    fun handlerOnClick(function: () -> Unit)
}