package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.databinding.FragmentInfoBinding


class InfoFragment : Fragment(R.layout.fragment_info) {

    lateinit var binding: FragmentInfoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentInfoBinding.bind(view)
        loadGifAnimation()


    }

    companion object {
        fun getInstance(): InfoFragment = InfoFragment()
    }

    fun loadGifAnimation(){
        Glide.with(requireContext())
                .asGif()
                .load(R.raw.own_animation)
                .centerCrop()
                .into(binding.gifInfoLogo)

    }
}