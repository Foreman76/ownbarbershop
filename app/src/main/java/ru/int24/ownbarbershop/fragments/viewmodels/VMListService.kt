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
import ru.int24.ownbarbershop.repositories.DataBaseRepository
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.utilits.ErrorType
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter
import javax.inject.Inject

class VMListService @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) : ViewModel(), RemoteErrorEmitter {

//    private val networkRepositoryImpl = NetworkRepositoryImpl()

    val param: ParamForService = makeParametersForGetServices()
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isErrorMessage: MutableLiveData<String> = MutableLiveData()
    val isErrorMessage:LiveData<String> = _isErrorMessage
    private val _isShowChose: MutableLiveData<Boolean> = MutableLiveData()
    val isShowChose:LiveData<Boolean> = _isShowChose
    private lateinit var dataBaseRepository: DataBaseRepository

    private val listService: MutableLiveData<MutableList<DomServices>?> = MutableLiveData()
    private val listServiceFromCash: MutableLiveData<MutableList<DomServices>> = MutableLiveData()

    fun getServiceVM() {

        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true
            listService.postValue(networkRepositoryImpl.getServices(param, this@VMListService))
            _isLoading.value = false

        }

    }

    fun getServiceFromCashVM(){
        viewModelScope.launch(Dispatchers.Main){
            listServiceFromCash.postValue(mutableListOf())  //Заглушка
            if (listServiceFromCash.value?.size == 0){ _isShowChose.value = false}

        }
    }

    fun changeShowChose(flag:Boolean){
        _isShowChose.value = flag
    }

    fun getService(): LiveData<MutableList<DomServices>?> = listService
    fun getServiceFromcash(): LiveData<MutableList<DomServices>> = listServiceFromCash

    fun makeParametersForGetServices(): ParamForService {
        //Здесь доделать запрос из базы или из преф
        return ParamForService( null, DefConfig.id,
            null, null )
    }

    override fun onError(msg: String) {
        _isErrorMessage.value = msg
    }

    override fun onError(errorType: ErrorType) {
        _isErrorMessage.value = "Ошибка работы с интернет"
    }
}