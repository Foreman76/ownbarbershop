package ru.int24.ownbarbershop.utilits

import com.squareup.picasso.Picasso
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.databinding.FragmentProfileBinding
import ru.int24.ownbarbershop.fragments.ProfileFragment
import ru.int24.ownbarbershop.models.domen.DomSettings

fun ProfileFragment.handlerBindDomSettingsExt(domSettings: DomSettings, binding: FragmentProfileBinding, myPicasso: Picasso){

    if (domSettings.phone.length == 10){
        val phone1 = domSettings.phone.substring(0,3)
        val phone2 = domSettings.phone.substring(3,6)
        val phone3 = domSettings.phone.substring(6,8)
        val phone4 = domSettings.phone.substring(8,10)
        val phoneText = "+7($phone1) $phone2-$phone3-$phone4"
        binding.idTextPhonePersonProfile.text = phoneText
    }else{
        binding.idTextPhonePersonProfile.text = domSettings.phone
    }


    binding.idNamePersonProfile.text = domSettings.userName
    binding.idTextEmailPersonProfile.text = domSettings.userEmail
    myPicasso.load(domSettings.avatar).placeholder(R.drawable.placeholder_avatar).into(binding.idLogoAvatarPersonProfile)
}
