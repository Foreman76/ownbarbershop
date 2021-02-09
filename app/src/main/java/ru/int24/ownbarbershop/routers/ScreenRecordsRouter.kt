package ru.int24.ownbarbershop.routers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.`interface`.ScreenChangeBase
import ru.int24.ownbarbershop.fragments.FutureRecordsFragment
import ru.int24.ownbarbershop.fragments.PastRecordsFragment


class ScreenRecordsRouter(private var child_fm: FragmentManager ): ScreenChangeBase {

    private lateinit var bFragment: Fragment

    override fun changeScreen(idBtn: Int) {
        when (idBtn) {
            R.id.button1 -> bFragment = FutureRecordsFragment.getInstance()
            R.id.button2 -> bFragment = PastRecordsFragment.getInstance()
            else -> bFragment = FutureRecordsFragment.getInstance()
        }
        child_fm.beginTransaction()
            .replace(R.id.fragment_records_container, bFragment)
            .commitNow()
    }

}