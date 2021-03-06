package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.int24.ownbarbershop.MainActivity
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.databinding.FragmentCreateOrderBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMCreateOrderFragment
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.utilits.ProgressIndicator
import ru.int24.ownbarbershop.utilits.initBaseRules
import javax.inject.Inject


class CreateOrderFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var vmCreateOrderFragment: VMCreateOrderFragment
    private var _binding: FragmentCreateOrderBinding? = null
    private val binding get() = _binding!!
    private val router: CommonRouter = CommonRouter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this@CreateOrderFragment)
        _binding = FragmentCreateOrderBinding.inflate(inflater, container, false)
        vmCreateOrderFragment = ViewModelProvider(this, modelFactory).get(VMCreateOrderFragment::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBaseRules(router, activity as MainActivity)
        initObservers()
        vmCreateOrderFragment.createOrderVM()
    }

    private fun initObservers() {
        vmCreateOrderFragment.isLoading.observe(viewLifecycleOwner, {ProgressIndicator.showHideProgress(it, binding.idCreateOrderLoader)})
        vmCreateOrderFragment.isErrorMsg.observe(viewLifecycleOwner, {router.routeThisFragmentToErrorScreen(it, R.id.action_createOrderFragment_to_errorFragment)})
        vmCreateOrderFragment.isErrorOrder.observe(viewLifecycleOwner, {handlerCreateOrder(it)})
    }

    private fun handlerCreateOrder(isErrorOrder: Boolean?) {
        when(isErrorOrder){
            true -> showNegativeImage()
            false -> showPositiveImage()
        }
    }

    private fun showPositiveImage() {
//        binding.idImageResult.setImageResource()
        binding.idTextCreateOrder.text = getString(R.string.text_ok_create_order)
    }

    private fun showNegativeImage() {
//        binding.idImageResult.setImageResource()
        binding.idTextCreateOrder.text = getString(R.string.text_error_try_create_order)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }
}