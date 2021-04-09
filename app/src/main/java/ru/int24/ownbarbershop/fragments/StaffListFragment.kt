package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.int24.ownbarbershop.UiInterface.InterfaceArrowBack
import ru.int24.ownbarbershop.UiInterface.HideShowBottomNavView
import ru.int24.ownbarbershop.UiInterface.InterfaceStaffAdapter
import ru.int24.ownbarbershop.databinding.FragmentStaffListBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.adapters.StaffAdapter
import ru.int24.ownbarbershop.fragments.viewmodels.VMStaffListFragment
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.utilits.ProgressIndicator
import javax.inject.Inject

class StaffListFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    @Inject lateinit var staffAdapter: StaffAdapter
    private var _binding: FragmentStaffListBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmStaffListFragment: VMStaffListFragment
    private val router = CommonRouter(this)



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentStaffListBinding.inflate(inflater, container,false)
        val view = binding.root
        App.appComponent.inject(this@StaffListFragment)
        vmStaffListFragment = ViewModelProvider(this, modelFactory).get(VMStaffListFragment::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        vmStaffListFragment.getStaffVM().observe(viewLifecycleOwner, { staffAdapter.refreshAdapter(it)})
        vmStaffListFragment.isLoading.observe(viewLifecycleOwner, { ProgressIndicator.showHideProgress(it, binding.idStaffLoader)})
        vmStaffListFragment.isErrorMessage.observe(viewLifecycleOwner){ router.routeListStaffToErrorScreen(it)}

        (activity as InterfaceArrowBack).hideShowArrowBack(false)
        (activity as InterfaceArrowBack).handlerOnClick(){router.routeListStaffScreenToOrderScreen()}
        hideBottomNavView()
        showListStaff()
        attachListener()
    }

    private fun attachListener() {
        staffAdapter.attachCallBack(object : InterfaceStaffAdapter{
            override fun chooseStaff(item: DomStaff) {
                vmStaffListFragment.addStaffToDBVM(item)
                router.routeListStaffScreenToOrderScreen()
            }

            override fun showInfo(item: DomStaff) {
                router.routeStaffListFragmentToDetailStaffFragment(item)
            }
        })
    }

    private fun showListStaff() {
        vmStaffListFragment.getStaffFromNetVM()
        binding.idRcStaff.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.idRcStaff.adapter = staffAdapter
    }


    fun hideBottomNavView(){
        (activity as HideShowBottomNavView).hideBottomNavView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}