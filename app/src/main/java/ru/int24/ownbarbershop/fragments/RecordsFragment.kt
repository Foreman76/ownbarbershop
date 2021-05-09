package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.databinding.FragmentRecordsBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMRecordsFrsgment
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.routers.ScreenRecordsRouter
import javax.inject.Inject


class RecordsFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory

    private lateinit var vmRecordsFragment: VMRecordsFrsgment
    private var _binding: FragmentRecordsBinding? = null
    private val binding get() = _binding!!
    private lateinit var router: ScreenRecordsRouter
    private val commonRouter = CommonRouter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this@RecordsFragment)
        _binding = FragmentRecordsBinding.inflate(inflater, container, false)
        vmRecordsFragment = ViewModelProvider(this, modelFactory).get(VMRecordsFrsgment::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router = ScreenRecordsRouter(childFragmentManager)

        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_records))
        checkAuth()
        router.changeScreen(0)

        binding.recordsToggleButton.addOnButtonCheckedListener { togglebutton, checkedId, isChecked ->
            if (isChecked) {
                router.changeScreen(checkedId)
            }
        }

    }


    private fun checkAuth() {
        when(DefConfig.settings.userToken.isEmpty()){
            true ->commonRouter.routeThisFragmentToAnyFragment(R.id.action_recordsFragment_to_authFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}