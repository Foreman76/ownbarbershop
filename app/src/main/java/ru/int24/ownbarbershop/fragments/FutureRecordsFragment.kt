package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.databinding.FragmentFutureRecordsBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.adapters.RecordsAdapter
import ru.int24.ownbarbershop.fragments.viewmodels.VMFutureRecordsFragment
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.utilits.ProgressIndicator
import javax.inject.Inject

class FutureRecordsFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var vmFutureRecordsFragment: VMFutureRecordsFragment
    private var _binding: FragmentFutureRecordsBinding? = null
    private val binding get() = _binding!!
    private val adapter = RecordsAdapter()
    private val router = CommonRouter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.appComponent.inject(this@FutureRecordsFragment)
        _binding = FragmentFutureRecordsBinding.inflate(layoutInflater, container, false)
        vmFutureRecordsFragment = ViewModelProvider(this, modelFactory).get(VMFutureRecordsFragment::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmFutureRecordsFragment.isLoading.observe(viewLifecycleOwner, {ProgressIndicator.showHideProgress(it, binding.idFutureRecordsLoader)})
        vmFutureRecordsFragment.getRecordsVM().observe(viewLifecycleOwner, {adapter.refreshAdapter(it)})
        vmFutureRecordsFragment.isEmptyListRecords.observe(viewLifecycleOwner, {showLabelEmptyListRecords(it)})
        vmFutureRecordsFragment.isErrorMsg.observe(viewLifecycleOwner, {router.routeThisFragmentToErrorScreen(it, R.id.action_futureRecordsFragment_to_errorFragment)})
        showListRecords()
    }

    private fun showLabelEmptyListRecords(isEmptyList: Boolean?) {
        isEmptyList?.let {
            when(isEmptyList){
                true -> binding.idFutureRecordsNot.visibility = View.VISIBLE
                false -> binding.idFutureRecordsNot.visibility = View.GONE
            }

        }
    }


    private fun showListRecords() {
        vmFutureRecordsFragment.getRecordsFromNet()
        binding.rcFutureRecords.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcFutureRecords.adapter = adapter
    }


    companion object{
        fun getInstance(): FutureRecordsFragment = FutureRecordsFragment()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}