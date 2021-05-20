package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.databinding.FragmentPastRecordsBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.adapters.RecordsAdapter
import ru.int24.ownbarbershop.fragments.viewmodels.VMPastRecordsFragment
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.utilits.ProgressIndicator
import javax.inject.Inject

class PastRecordsFragment : Fragment(R.layout.fragment_past_records) {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var vmPastRecordsFragment: VMPastRecordsFragment
    private var _binding: FragmentPastRecordsBinding? = null
    private val binding get() = _binding!!
    private val adapter = RecordsAdapter()
    private val router = CommonRouter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.appComponent.inject(this@PastRecordsFragment)
        _binding = FragmentPastRecordsBinding.inflate(layoutInflater, container, false)
        vmPastRecordsFragment = ViewModelProvider(this, modelFactory).get(VMPastRecordsFragment::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmPastRecordsFragment.isLoading.observe(viewLifecycleOwner, { ProgressIndicator.showHideProgress(it, binding.idPastRecordsLoader)})
        vmPastRecordsFragment.getRecordsVM().observe(viewLifecycleOwner, {adapter.refreshAdapter(it)})
        vmPastRecordsFragment.isEmptyListRecords.observe(viewLifecycleOwner, {showLabelEmptyListRecords(it)})
        vmPastRecordsFragment.isErrorMsg.observe(viewLifecycleOwner, {router.routeThisFragmentToErrorScreen(it, R.id.action_pastRecordsFragment_to_errorFragment)})
        showListRecords()
    }

    private fun showLabelEmptyListRecords(isEmptyList: Boolean?) {
        isEmptyList?.let {
            when(isEmptyList){
                true -> binding.idPastRecordsNot.visibility = View.VISIBLE
                false -> binding.idPastRecordsNot.visibility = View.GONE
            }

        }
    }

    private fun showListRecords() {
        vmPastRecordsFragment.getRecordsFromNet()
        binding.rcPastRecords.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcPastRecords.adapter = adapter
    }


    companion object{

        fun getInstance(): PastRecordsFragment = PastRecordsFragment()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}