package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.databinding.FragmentPastRecordsBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMPastRecordsFragment
import javax.inject.Inject

class PastRecordsFragment : Fragment(R.layout.fragment_past_records) {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var vmFutureRecordsFragment: VMPastRecordsFragment
    private var _binding: FragmentPastRecordsBinding? = null
    private val binding = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.appComponent.inject(this@PastRecordsFragment)
        _binding = FragmentPastRecordsBinding.inflate(layoutInflater, container, false)
        vmFutureRecordsFragment = ViewModelProvider(this, modelFactory).get(VMPastRecordsFragment::class.java)
        return binding.root

    }





    companion object{

        fun getInstance(): PastRecordsFragment = PastRecordsFragment()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}