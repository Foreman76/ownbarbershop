package ru.int24.ownbarbershop.routers

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.config.DefConfig


interface RouteErrorScreen{
    fun routeListServiceToErrorScreen(errormessage: String)
    fun routeErrorToListService()

}

interface CommonRouteLogic{
    fun routeOrderScreenToListServiceScreen()
    fun routeListServiceScreenToOrderScreen()
}

class CommonRouter(private val fragment: Fragment): RouteErrorScreen, CommonRouteLogic {
    override fun routeErrorToListService() {
        fragment.apply { findNavController().navigate(R.id.action_errorFragment_to_orderFragment) }
    }

    private val key_error:String = DefConfig.key_error
    override fun routeListServiceToErrorScreen(errormessage: String) {
        val bundle = bundleOf(key_error to errormessage)
        fragment.apply { findNavController().navigate(R.id.action_listServiceFragment_to_errorFragment, bundle) }
    }

    override fun routeOrderScreenToListServiceScreen() {
        fragment.apply { findNavController().navigate(R.id.action_orderFragment_to_listServiceFragment) }
    }

    override fun routeListServiceScreenToOrderScreen() {
        fragment.apply { findNavController().navigate(R.id.action_listServiceFragment_to_orderFragment) }
    }

}


