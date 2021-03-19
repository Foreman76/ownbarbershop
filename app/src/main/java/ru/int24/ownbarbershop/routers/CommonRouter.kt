package ru.int24.ownbarbershop.routers

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.domen.DomStaff


interface RouteErrorScreen{
    fun routeListServiceToErrorScreen(errormessage: String)
    fun routeErrorToListService()

}

interface CommonRouteLogic{
    fun routeOrderScreenToListServiceScreen()
    fun routeListServiceScreenToOrderScreen()
    fun routeOrderScreenToListStaffScreen()
    fun routeListStaffScreenToOrderScreen()
    fun routeStaffListFragmentToDetailStaffFragment(staff:DomStaff)
    fun routeDetailStaffFragmentToListStaffFragment()
}

class CommonRouter(private val fragment: Fragment): RouteErrorScreen, CommonRouteLogic {
    override fun routeErrorToListService() {
        fragment.apply { findNavController().navigate(R.id.action_errorFragment_to_orderFragment) }
    }

    override fun routeStaffListFragmentToDetailStaffFragment(staff: DomStaff) {
        val bundel = bundleOf("staff" to staff)
        fragment.apply { findNavController().navigate(R.id.action_staffListFragment_to_detailStaffFragment, bundel) }
    }

    override fun routeDetailStaffFragmentToListStaffFragment() {
        fragment.apply { findNavController().navigate(R.id.action_detailStaffFragment_to_staffListFragment) }
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

    override fun routeOrderScreenToListStaffScreen() {
        fragment.apply { findNavController().navigate(R.id.action_orderFragment_to_staffListFragment) }
    }

    override fun routeListStaffScreenToOrderScreen() {
        fragment.apply { findNavController().navigate(R.id.action_staffListFragment_to_orderFragment) }
    }

}


