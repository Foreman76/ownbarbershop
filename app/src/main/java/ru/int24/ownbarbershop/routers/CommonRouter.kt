package ru.int24.ownbarbershop.routers

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.domen.DomStaff


interface RouteErrorScreen{

    fun routeThisFragmentToErrorScreen(errormessage: String, actionId:Int)

    fun routeErrorToOrderfragment()

}

interface CommonRouteLogic{

    fun routeStaffListFragmentToDetailStaffFragment(staff:DomStaff)

    fun routeThisFragmentToAnyFragment(actionId:Int)
    fun routeFragmentUp()
//    fun routerFragmentPopBackStack()

}

class CommonRouter(private val fragment: Fragment): RouteErrorScreen, CommonRouteLogic {

    override fun routeErrorToOrderfragment() {
        fragment.apply { findNavController().navigate(R.id.action_errorFragment_to_orderFragment) }
    }

    override fun routeStaffListFragmentToDetailStaffFragment(staff: DomStaff) {
        val bundel = bundleOf("staff" to staff)
        fragment.apply { findNavController().navigate(R.id.action_staffListFragment_to_detailStaffFragment, bundel) }
    }

    override fun routeThisFragmentToAnyFragment(actionId: Int) {
        fragment.apply { findNavController().navigate(actionId) }
    }

    override fun routeFragmentUp() {
        fragment.apply { findNavController().navigateUp() }
    }

    override fun routeThisFragmentToErrorScreen(errormessage: String, actionId: Int) {
        val bundle = bundleOf(DefConfig.key_error to errormessage)
        fragment.apply { findNavController().navigate(actionId, bundle) }
    }

}


