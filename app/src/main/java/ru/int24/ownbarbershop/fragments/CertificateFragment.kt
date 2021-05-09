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
import ru.int24.ownbarbershop.databinding.FragmentCertificateBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMCertificateFragment
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.utilits.initBaseRules
import javax.inject.Inject


class CertificateFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory

    private var _binding:FragmentCertificateBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmCertificateFragment: VMCertificateFragment
    private val router = CommonRouter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this@CertificateFragment)
        _binding = FragmentCertificateBinding.inflate(layoutInflater, container, false)
        vmCertificateFragment = ViewModelProvider(this@CertificateFragment, modelFactory).get(VMCertificateFragment::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_certificate))
        binding.idNotDataSubscription.visibility = View.VISIBLE
        initBaseRules(router, activity as MainActivity)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}