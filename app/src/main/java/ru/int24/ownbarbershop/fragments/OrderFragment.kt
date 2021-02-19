package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.databinding.FragmentOrderBinding
import ru.int24.ownbarbershop.fragments.viewmodels.ViewModelOrderFragment
import ru.int24.ownbarbershop.routers.CommonRouter


class OrderFragment : Fragment() {

    private val viewModelOrderFragment: ViewModelOrderFragment by viewModels()
    private val router:CommonRouter = CommonRouter(this)
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_order))

        binding.idOrderTextService.setOnClickListener{
            router.routeOrderScreenToListServiceScreen()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}