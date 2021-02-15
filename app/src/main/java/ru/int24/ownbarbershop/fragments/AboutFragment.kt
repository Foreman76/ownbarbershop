package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_about.*
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.databinding.FragmentAboutBinding
import ru.int24.ownbarbershop.routers.ScreenAboutRouter


class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!
    private lateinit var router: ScreenAboutRouter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router = ScreenAboutRouter(childFragmentManager)
        router.changeScreen(0)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_about))
        loadLogo()


        toggle_button_group.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->

            if (isChecked) {
                router.changeScreen(checkedId)
            }
        }
    }

    fun loadLogo(){
        Glide.with(requireContext())
                .load(R.drawable.stndrt_blue)
                .circleCrop()
                .into(binding.barberAboutLogo)
    }

}


