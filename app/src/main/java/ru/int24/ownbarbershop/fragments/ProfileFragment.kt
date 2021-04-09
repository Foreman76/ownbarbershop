package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.databinding.FragmentProfileBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMProfileFragment
import javax.inject.Inject


class ProfileFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmProfileFragment: VMProfileFragment


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        App.appComponent.inject(this@ProfileFragment)
        vmProfileFragment = ViewModelProvider(this@ProfileFragment).get(VMProfileFragment::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Либо меню либо фрагмент входа если пользователь не авторизован
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_profile))
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}