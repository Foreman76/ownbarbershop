package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.`interface`.BarberToolBar


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Либо меню либо фрагмент входа если пользователь не авторизован
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_profile))
    }
}