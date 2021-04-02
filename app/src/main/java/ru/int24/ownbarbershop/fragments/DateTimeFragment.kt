package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.ArrowBack
import ru.int24.ownbarbershop.UiInterface.HideShowBottomNavView
import ru.int24.ownbarbershop.databinding.FragmentDateTimeBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMDateTimeFragment
import ru.int24.ownbarbershop.routers.CommonRouter
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDateTimeBinding.inflate(inflater, container, false)
        App.appComponent.inject(this@DateTimeFragment)
        vmDateTimeFragment = ViewModelProvider(this, modelFactory).get(VMDateTimeFragment::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBaseRules()
        createDatePicker()
        vmDateTimeFragment.isLoading.observe(viewLifecycleOwner, { ProgressIndicator.showHideProgress(it, binding.idTimeLoader)})
        vmDateTimeFragment.isErrorMessage.observe(viewLifecycleOwner, {router.routeDateTimeFragmentToErrorScreen(it)})

    }

    fun handlerChooseDateFromDateRicker(date: Long?){
        addDateToDatabase(date)
        vmDateTimeFragment.getAvailableSessionsFromNet()

    }

    private fun addDateToDatabase(date: Long?) {
        vmDateTimeFragment.addSesionVM(date)
    }

    fun createDatePicker(){
        datePicker = GetMaterialDatePicker.getMaterialDatePicker(getString(R.string.text_chose_date_session))
        datePicker.addOnPositiveButtonClickListener { handlerChooseDateFromDateRicker(it) }
        datePicker.addOnCancelListener { router.routeDateTimeScreenToOrderScreen() }
        datePicker.show(childFragmentManager, "Tag")
    }

    fun initBaseRules(){
        (activity as ArrowBack).hideShowArrowBack(false)
        (activity as ArrowBack).handlerOnClick { router.routeDateTimeScreenToOrderScreen() }
        (activity as HideShowBottomNavView).hideBottomNavView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}


