package ru.int24.ownbarbershop.routers

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.domen.DomStaff


interface RouteErrorScreen{
    fun routeListServiceToErrorScreen(errormessage: String)
    fun routeListStaffToErrorScreen(errormessage: String)
    fun routeDateTimeFragmentToErrorScreen(errormessage: String)


    fun routeErrorToOrderfragment()

}

interface CommonRouteLogic{
    fun routeOrderScreenToListServiceScreen()
    fun routeListServiceScreenToOrderScreen()
    fun routeOrderScreenToListStaffScreen()
    fun routeListStaffScreenToOrderScreen()
    fun routeStaffListFragmentToDetailStaffFragment(staff:DomStaff)
    fun routeDetailStaffFragmentToListStaffFragment()
    fun routeOrderScreenToDateTimeScreen()
    fun routeDateTimeScreenToOrderScreen()
}

class CommonRouter(private val fragment: Fragment): RouteErrorScreen, CommonRouteLogic {
    override fun routeErrorToOrderfragment() {
        fragment.apply { findNavController().navigate(R.id.action_errorFragment_to_orderFragment) }
    }

    override fun routeStaffListFragmentToDetailStaffFragment(staff: DomStaff) {
        val bundel = bundleOf("staff" to staff)
        fragment.apply { findNavController().navigate(R.id.action_staffListFragment_to_detailStaffFragment, bundel) }
    }

    override fun routeDetailStaffFragmentToListStaffFragment() {
        fragment.apply { findNavController().navigate(R.id.action_detailStaffFragment_to_staffListFragment) }
    }

    override fun routeOrderScreenToDateTimeScreen() {
        fragment.apply { findNavController().navigate(R.id.action_orderFragment_to_dateTimeFragment) }
    }

    override fun routeDateTimeScreenToOrderScreen() {
        fragment.apply { findNavController().navigate(R.id.action_dateTimeFragment_to_orderFragment) }
    }


    override fun routeListServiceToErrorScreen(errormessage: String) {
        val bundle = bundleOf(DefConfig.key_error to errormessage)
        fragment.apply { findNavController().navigate(R.id.action_listServiceFragment_to_errorFragment, bundle) }
    }

    override fun routeListStaffToErrorScreen(errormessage: String) {
        val bundle = bundleOf(DefConfig.key_error to errormessage)
        fragment.apply { findNavController().navigate(R.id.action_staffListFragment_to_errorFragment, bundle) }
    }

    override fun routeDateTimeFragmentToErrorScreen(errormessage: String) {
        val bundle = bundleOf(DefConfig.key_error to errormessage)
        fragment.apply { findNavController().navigate(R.id.action_dateTimeFragment_to_errorFragment, bundle) }
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


