package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.databinding.FragmentRecordsBinding
import ru.int24.ownbarbershop.routers.ScreenRecordsRouter


class RecordsFragment : Fragment() {

    private var _binding: FragmentRecordsBinding? = null
    private val binding get() = _binding!!
    private lateinit var router: ScreenRecordsRouter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router = ScreenRecordsRouter(childFragmentManager)

        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_records))
        router.changeScreen(0)

        binding.recordsToggleButton.addOnButtonCheckedListener { togglebutton, checkedId, isChecked ->
            if (isChecked) {
                router.changeScreen(checkedId)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}