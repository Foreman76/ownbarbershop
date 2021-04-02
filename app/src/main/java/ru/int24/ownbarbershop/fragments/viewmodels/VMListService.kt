package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.ParamForService
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.ErrorType
import ru.int24.ownbarbershop.utilits.GetResource
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter
import ru.int24.ownbarbershop.utilits.RequestParameters
import javax.inject.Inject

class VMListService @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl,
                                        private val usesCaseBaseRepositoryImpl: UsesCaseBaseRepositoryImpl,
                                        private val getResource: GetResource) : ViewModel(), RemoteErrorEmitter {

    private lateinit var paramforService: ParamForService
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isErrorMessage: MutableLiveData<String> = MutableLiveData()
    val isErrorMessage:LiveData<String> = _isErrorMessage
    private val _isShowChose: MutableLiveData<Boolean> = MutableLiveData()
    val isShowChose:LiveData<Boolean> = _isShowChose

    private val listService: MutableLiveData<MutableList<DomServices>?> = MutableLiveData()

    fun getServiceVM() {

        viewModelScope.launch(Dispatchers.Main) {

            _isLoading.value = true

            paramforService = RequestParameters.makeParametersForService(usesCaseBaseRepositoryImpl.getAllStaff())
            listService.postValue(networkRepositoryImpl.getServices(paramforService, this@VMListService))
            _isLoading.value = false

        }

    }

    fun changeShowChose(flag:Boolean){
        _isShowChose.value = flag
    }

    fun getService(): LiveData<MutableList<DomServices>?> = listService

    fun deleteServiceVM(services: DomServices){
        viewModelScope.launch(Dispatchers.Main) {
            usesCaseBaseRepositoryImpl.deleteService(services)
        }
    }

    fun addService(services: DomServices){
        viewModelScope.launch(Dispatchers.Main) {
            usesCaseBaseRepositoryImpl.addService(services)
        }
    }

    override fun onError(msg: String) {
        _isErrorMessage.value = msg
    }

    override fun onError(errorType: ErrorType) {
        _isErrorMessage.value = getResource.getString(R.string.text_error_network)
    }


}