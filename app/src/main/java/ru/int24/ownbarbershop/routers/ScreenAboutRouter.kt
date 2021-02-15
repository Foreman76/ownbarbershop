package ru.int24.ownbarbershop.routers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.ScreenChangeBase
import ru.int24.ownbarbershop.fragments.ContactFragment
import ru.int24.ownbarbershop.fragments.FeedBackFragment
import ru.int24.ownbarbershop.fragments.InfoFragment


class ScreenAboutRouter(private var child_fm: FragmentManager): ScreenChangeBase{
    private lateinit var bFragment: Fragment
    override fun changeScreen(idBtn: Int) {
        when (idBtn) {
            R.id.About_ToggleButton0 -> bFragment = InfoFragment.getInstance()
            R.id.About_ToggleButton1 -> bFragment = FeedBackFragment.getInstance()
            R.id.About_ToggleButton2 -> bFragment = ContactFragment.getInstance()
            else -> bFragment = InfoFragment()
        }
        child_fm.beginTransaction()
            .replace(R.id.fragment_container_about, bFragment)
            .commitNow()
    }

}

