package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.fragments.viewmodels.ViewModelOrderFragment


class OrderFragment : Fragment(R.layout.fragment_order) {

    private val viewModelOrderFragment: ViewModelOrderFragment by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_order))

        viewModelOrderFragment.getServiceVM()
    }

}