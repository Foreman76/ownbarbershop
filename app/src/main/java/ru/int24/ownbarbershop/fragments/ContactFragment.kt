package ru.int24.ownbarbershop.fragments

import androidx.fragment.app.Fragment
import ru.int24.ownbarbershop.R


class ContactFragment : Fragment(R.layout.fragment_contact) {
    companion object{
        fun getInstance():ContactFragment = ContactFragment()
    }

}