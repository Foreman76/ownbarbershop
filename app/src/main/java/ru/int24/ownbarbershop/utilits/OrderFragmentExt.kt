package ru.int24.ownbarbershop.utilits

import android.view.View
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.databinding.FragmentOrderBinding
import ru.int24.ownbarbershop.fragments.OrderFragment
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomStaff


fun OrderFragment.onBindingService(listService: MutableList<DomServices>,
                                    binding: FragmentOrderBinding){

    fun showHideTextService(textService:String, flag:Int, resourceIcon: Int){
        binding.idOrderListTextService.text = textService
        binding.idOrderListTextService.visibility = flag
        binding.idServiceIconDelete.visibility = flag
        binding.idServiceIcon.setImageResource(resourceIcon)
    }

    var textService: String = ""
    if (listService.size != 0){
        listService.forEach { service ->
            textService = textService+" "+service.title
            showHideTextService(textService, View.VISIBLE, R.drawable.ic_baseline_check_circle_outline_24_active)
        }

    }else { showHideTextService(textService, View.GONE, R.drawable.ic_baseline_check_circle_outline_24) }


}

fun OrderFragment.onBindingStaff(staff: DomStaff?, binding: FragmentOrderBinding){

    fun showHidetextStaff(visible:Int, resourceIcon:Int){
        binding.idOrderListTextStaff.text = staff?.name ?: ""
        binding.idOrderListTextStaff.visibility = visible
        binding.idStuffIconDelete.visibility = visible
        binding.idStuffIcon.setImageResource(resourceIcon)
    }

    if ( staff != null) {
        showHidetextStaff(View.VISIBLE, R.drawable.ic_baseline_groups_24_active)
    }else{
       showHidetextStaff(View.GONE, R.drawable.ic_baseline_groups_24)
    }

}
