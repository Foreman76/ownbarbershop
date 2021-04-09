package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.HideShowBottomNavView
import ru.int24.ownbarbershop.UiInterface.InterfaceArrowBack
import ru.int24.ownbarbershop.UiInterface.InterfaceSessionAdapter
import ru.int24.ownbarbershop.databinding.FragmentDateTimeBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.adapters.SessionAdapter
import ru.int24.ownbarbershop.fragments.viewmodels.VMDateTimeFragment
import ru.int24.ownbarbershop.models.domen.DomSession
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.utilits.GetDataFormat
import ru.int24.ownbarbershop.utilits.GetMaterialDatePicker
import ru.int24.ownbarbershop.utilits.ProgressIndicator
import java.util.*
import javax.inject.Inject

class DateTimeFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory

    private var _binding: FragmentDateTimeBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmDateTimeFragment: VMDateTimeFragment
    private val router:CommonRouter = CommonRouter(this)
    private lateinit var datePicker: MaterialDatePicker<Long>
    private val sessionAdapter = SessionAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDateTimeBinding.inflate(inflater, container, false)
        App.appComponent.inject(this@DateTimeFragment)
        vmDateTimeFragment = ViewModelProvider(this, modelFactory).get(VMDateTimeFragment::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBaseRules()
        vmDateTimeFragment.getListWorkDaysVM().observe(viewLifecycleOwner, {createDatePicker(it)})
        vmDateTimeFragment.isLoading.observe(viewLifecycleOwner, { ProgressIndicator.showHideProgress(it, binding.idTimeLoader)})
        vmDateTimeFragment.isErrorMessage.observe(viewLifecycleOwner, {router.routeDateTimeFragmentToErrorScreen(it)})
        vmDateTimeFragment.getListSessionTimeVM().observe(viewLifecycleOwner, { showListSessionTime(it)})
        vmDateTimeFragment.getAvailableWorkdaysFromNet()
    }

    private fun attachSessionAdapterClickListener() {
        sessionAdapter.attachHadlerClickItem(object: InterfaceSessionAdapter{
            override fun handlerChooseSessionTime(sessionTime: DomSession) {
                vmDateTimeFragment.addSesionVM(sessionTime.datetime.replaceAfter("+", GetDataFormat.getCurrentOffset()))
                router.routeDateTimeScreenToOrderScreen()
            }
        })
    }


    private fun showListSessionTime(listSessionTime: MutableList<DomSession>?) {
        sessionAdapter.refreshAdapter(listSessionTime)
        binding.idRcTime.layoutManager = GridLayoutManager(context,3, LinearLayoutManager.VERTICAL, false)
        binding.idRcTime.adapter = sessionAdapter
        attachSessionAdapterClickListener()
    }

    fun handlerChooseDateFromDatePicker(date: Long?){
        vmDateTimeFragment.getAvailableSessionsFromNet(date)

    }


    fun createDatePicker(listWorkDays: List<String>){
        datePicker = GetMaterialDatePicker.getMaterialDatePicker(getString(R.string.text_chose_date_session), listWorkDays)
        datePicker.addOnPositiveButtonClickListener { handlerChooseDateFromDatePicker(it) }
        datePicker.addOnNegativeButtonClickListener { router.routeDateTimeScreenToOrderScreen() }
        datePicker.show(childFragmentManager, "Tag")
    }

    fun initBaseRules(){
        (activity as InterfaceArrowBack).hideShowArrowBack(false)
        (activity as InterfaceArrowBack).handlerOnClick { router.routeDateTimeScreenToOrderScreen() }
        (activity as HideShowBottomNavView).hideBottomNavView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}


