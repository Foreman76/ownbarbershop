package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

class VMListService: ViewModel(), RemoteErrorEmitter {

    val networkRepositoryImpl = NetworkRepositoryImpl(this )
    val param: ParamForService = makeParametersForGetServices()
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val listService: MutableLiveData<MutableList<DomServices>?> = MutableLiveData()

    fun getServiceVM() {

        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true
            listService.postValue(networkRepositoryImpl.getServices(param))
            _isLoading.value = false
        }

    }




    fun getService(): LiveData<MutableList<DomServices>?> = listService

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onError(errorType: ErrorType) {
        TODO("Not yet implemented")
    }

    fun makeParametersForGetServices(): ParamForService {
        //Здесь доделать запрос из базы или из преф
        return ParamForService( null, DefConfig.id,
            null, null )
    }
}