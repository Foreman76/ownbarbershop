package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.databinding.FragmentGoodsBinding
import ru.int24.ownbarbershop.routers.CommonRouter


class GoodsFragment : Fragment() {

    private var _binding: FragmentGoodsBinding? = null
    private val binding get() = _binding!!
    private val router = CommonRouter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentGoodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_goods))
        checkAuth()
    }

    private fun checkAuth() {
        when(DefConfig.settings.userToken.isEmpty()){
            true ->router.routeThisFragmentToAnyFragment(R.id.action_goodsFragment_to_authFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}