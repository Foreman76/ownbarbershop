package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.int24.ownbarbershop.databinding.FragmentAuthBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMAuthFragment
import javax.inject.Inject


class AuthFragment : Fragment() {


    @Inject lateinit var modelFactory: ViewModelProvider.Factory

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmAuthFragment: VMAuthFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        App.appComponent.inject(this@AuthFragment)
        vmAuthFragment = ViewModelProvider(this@AuthFragment).get(VMAuthFragment::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}