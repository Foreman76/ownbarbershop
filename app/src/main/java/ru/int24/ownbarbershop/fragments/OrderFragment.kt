package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.UiInterface.HideShowBottomNavView
import ru.int24.ownbarbershop.databinding.FragmentOrderBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.ViewModelOrderFragment
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.utilits.onBindingService
import ru.int24.ownbarbershop.utilits.onBindingStaff
import javax.inject.Inject


class OrderFragment : Fragment() {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private lateinit var vmOrderFragment: ViewModelOrderFragment
    private val router:CommonRouter = CommonRouter(this)
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root
        App.appComponent.inject(this@OrderFragment)
        vmOrderFragment = ViewModelProvider(this,modelFactory).get(ViewModelOrderFragment::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_order))

        vmOrderFragment.getServiceFromVM().observe(viewLifecycleOwner, {showService(it)})
        vmOrderFragment.getStaffVM().observe(viewLifecycleOwner, {showStaff(it)})

        binding.idService.setOnClickListener{ routeOrderScreenToListServiceScreen() }
        binding.idStaff.setOnClickListener { routeOrderScreenToListStaffScreen() }


        binding.idServiceIconDelete.setOnClickListener { deleteServce() }
        binding.idStuffIconDelete.setOnClickListener { deleteStaff() }
        showBottomNavView()
        vmOrderFragment.getServiceFromDBVM()
        vmOrderFragment.getStaffFromDBVM()
    }

    private fun routeOrderScreenToListStaffScreen() {
        deleteStaff()
        router.routeOrderScreenToListStaffScreen()
    }

    private fun routeOrderScreenToListServiceScreen() {
        deleteServce()
        router.routeOrderScreenToListServiceScreen()
    }

    private fun showStaff(staff: DomStaff?) {
      this.onBindingStaff(staff, binding)
    }

    private fun deleteServce() {
        vmOrderFragment.deleteServices()
    }

    private fun deleteStaff(){
        vmOrderFragment.deleteStaff()
    }

    fun showService(listService: MutableList<DomServices>){
        this.onBindingService(listService, binding)
    }

    fun showBottomNavView(){
        (activity as HideShowBottomNavView).showBottomNavView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}