package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.models.domen.ParamForStaff
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.ErrorType
import ru.int24.ownbarbershop.utilits.GetResource
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter
import ru.int24.ownbarbershop.utilits.RequestParameters
import javax.inject.Inject

class VMStaffListFragment @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl,
                                              private val usesCaseBaseRepositoryImpl: UsesCaseBaseRepositoryImpl,
                                              private val getResource: GetResource) : ViewModel(), RemoteErrorEmitter {

    private lateinit var param:ParamForStaff
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isErrorMessage: MutableLiveData<String> = MutableLiveData()
    val isErrorMessage: LiveData<String> = _isErrorMessage
    private var listService:MutableList<DomServices>? = null
    private val listStaff: MutableLiveData<MutableList<DomStaff>?> = MutableLiveData()




    fun getStaffFromNetVM() {
        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true

            listService = usesCaseBaseRepositoryImpl.getAllService()
            param = RequestParameters.makeParametersForStaff(listService)
             listStaff.postValue(networkRepositoryImpl.getStaff(param, this@VMStaffListFragment))
            _isLoading.value = false
        }
    }

    fun getStaffVM():LiveData<MutableList<DomStaff>?> = listStaff
    fun addStaffToDBVM(staff: DomStaff){
        viewModelScope.launch(Dispatchers.Main) {
            usesCaseBaseRepositoryImpl.addStaff(staff)
        }
    }

    override fun onError(msg: String) {
       _isErrorMessage.value = msg
    }

    override fun onError(errorType: ErrorType) {
        _isErrorMessage.value = getResource.getString(R.string.text_error_network)
    }


}