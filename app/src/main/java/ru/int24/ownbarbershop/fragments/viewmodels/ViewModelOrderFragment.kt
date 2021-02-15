package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.ParamForService
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.utilits.ErrorType
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter

class ViewModelOrderFragment: ViewModel(), RemoteErrorEmitter {

    val networkRepositoryImpl = NetworkRepositoryImpl(this )
    val param: ParamForService  = makeParametersForGetServices()
    fun getServiceVM() {
        viewModelScope.launch(Dispatchers.Main) {
            val listDomServices: List<DomServices>? = networkRepositoryImpl.getServices(param)
            //check for null
            val a=0
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

    override fun onError(msg: String) {
        val a = 0
    }

    override fun onError(errorType: ErrorType) {
        val a = 0

    }


}

