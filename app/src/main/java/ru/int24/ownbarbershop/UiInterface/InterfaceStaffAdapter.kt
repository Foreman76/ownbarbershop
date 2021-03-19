package ru.int24.ownbarbershop.UiInterface

import ru.int24.ownbarbershop.models.domen.DomStaff

interface InterfaceStaffAdapter {

    fun chooseStaff(item:DomStaff)
    fun showInfo(item: DomStaff)

}