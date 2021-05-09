package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.MainActivity
import ru.int24.ownbarbershop.UiInterface.HideShowBottomNavView
import ru.int24.ownbarbershop.UiInterface.InterfaceArrowBack
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.domen.DomSettings
import ru.int24.ownbarbershop.routers.CommonRouter

fun updateDefConfig(domSettings: DomSettings){
    DefConfig.settings = domSettings
}

fun initBaseRules(router:CommonRouter, activity: MainActivity){
    (activity as InterfaceArrowBack).hideShowArrowBack(false)
    (activity as InterfaceArrowBack).handlerOnClick { router.routeFragmentUp() }
    (activity as HideShowBottomNavView).hideBottomNavView()
}

fun initBaseRulesMain(activity: MainActivity){
    (activity as HideShowBottomNavView).showBottomNavView()
    (activity as InterfaceArrowBack).hideShowArrowBack(true)
}