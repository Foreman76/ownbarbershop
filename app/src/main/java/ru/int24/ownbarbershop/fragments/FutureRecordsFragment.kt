package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.int24.ownbarbershop.databinding.FragmentFutureRecordsBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.adapters.RecordsAdapter
import ru.int24.ownbarbershop.fragments.viewmodels.VMFutureRecordsFragment
import javax.inject.Inject

class FutureRecordsFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var vmFutureRecordsFragment: VMFutureRecordsFragment
    private var _binding: FragmentFutureRecordsBinding? = null
    private val binding = _binding!!
    private val adapter = RecordsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.appComponent.inject(this@FutureRecordsFragment)
        _binding = FragmentFutureRecordsBinding.inflate(layoutInflater, container, false)
        vmFutureRecordsFragment = ViewModelProvider(this, modelFactory).get(VMFutureRecordsFragment::class.java)
        return binding.root

    }

    companion object{
        fun getInstance(): FutureRecordsFragment = FutureRecordsFragment()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}