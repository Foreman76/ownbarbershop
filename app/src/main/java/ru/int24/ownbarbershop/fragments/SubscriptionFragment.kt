package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.int24.ownbarbershop.MainActivity
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.databinding.FragmentSubscriptionBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMSubscriptionFragment
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.utilits.initBaseRules
import javax.inject.Inject


class SubscriptionFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory

    private var _binding: FragmentSubscriptionBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmSubscriptionFragment: VMSubscriptionFragment
    private val router = CommonRouter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSubscriptionBinding.inflate(layoutInflater, container, false)
        App.appComponent.inject(this@SubscriptionFragment)
        vmSubscriptionFragment = ViewModelProvider(this@SubscriptionFragment, modelFactory).get(VMSubscriptionFragment::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBaseRules(router, activity as MainActivity)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_subscription))
        binding.idNotDataSubscription.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}