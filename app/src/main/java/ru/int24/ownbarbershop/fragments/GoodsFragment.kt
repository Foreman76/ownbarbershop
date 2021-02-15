package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar


class GoodsFragment : Fragment(R.layout.fragment_goods) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_goods))
    }
}