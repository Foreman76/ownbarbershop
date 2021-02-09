package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.ParamForService
import ru.int24.ownbarbershop.models.netresult.NetResult
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl

class ViewModelOrderFragment: ViewModel() {

    val networkRepositoryImpl = NetworkRepositoryImpl()
    val param: ParamForService  = makeParametersForGetServices()
    fun getServiceVM() {
        viewModelScope.launch(Dispatchers.Main) {
            val serviceResponse = networkRepositoryImpl.getServices(param)
            when (serviceResponse) {
                is NetResult.NetworkError -> dosomthing()
                is NetResult.GenericError -> showGenericError(serviceResponse.error)
                is NetResult.Success -> showSuccess(serviceResponse.value)
            }
        }
    }

    fun makeParametersForGetServices(): ParamForService {
        //Здесь доделать запрос из базы или из преф
        return ParamForService( null, DefConfig.id,
                null, null )
    }

    fun dosomthing(){}
    fun showGenericError(er:String?){}
    fun showSuccess(value: List<DomServices>) {}


}

