package ru.int24.ownbarbershop.routers

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.int24.ownbarbershop.R


interface RouteErrorScreen{
    fun routeListServiceToErrorScreen(errormessage: String)

}

interface CommonRouteLogic{
    fun routeOrderScreenToListServiceScreen()
}

class CommonRouter(private val fragment: Fragment): RouteErrorScreen, CommonRouteLogic {

    private val key_error:String = "ru.int24.ownbarbershop.routers.errormessage"
    override fun routeListServiceToErrorScreen(errormessage: String) {
        val bundle = bundleOf(key_error to errormessage)
        fragment.apply { findNavController().navigate(R.id.action_listServiceFragment_to_errorFragment, bundle) }
    }

    override fun routeOrderScreenToListServiceScreen() {
        fragment.apply { findNavController().navigate(R.id.action_orderFragment_to_listServiceFragment) }
    }

}


