package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.models.domen.DomRecords
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.utilits.ErrorType
import ru.int24.ownbarbershop.utilits.GetResource
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter
import ru.int24.ownbarbershop.utilits.TypeRecord
import javax.inject.Inject

class VMFutureRecordsFragment @Inject constructor (private val networkRepositoryImpl: NetworkRepositoryImpl,
                                                    private val getResource: GetResource): ViewModel(), RemoteErrorEmitter {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading
    private val listDomRecords: MutableLiveData<ArrayList<DomRecords>?> = MutableLiveData()
    private val _isErrorMsg:MutableLiveData<String> = MutableLiveData()
    val isErrorMsg: LiveData<String> = _isErrorMsg
    private val _isEmptyListrecords: MutableLiveData<Boolean> = MutableLiveData()
    val isEmptyListRecords: LiveData<Boolean> = _isEmptyListrecords


    fun getRecordsVM(): LiveData<ArrayList<DomRecords>?> = listDomRecords

    fun getRecordsFromNet(){

        viewModelScope.launch(Dispatchers.Main){
            _isLoading.value = true
            listDomRecords.value = networkRepositoryImpl.getUserRecords(TypeRecord.FutureRecords(), this@VMFutureRecordsFragment)

            _isEmptyListrecords.value = listDomRecords.value.isNullOrEmpty()
            _isLoading.value = false
        }
    }



    override fun onError(msg: String) {
        _isErrorMsg.value = msg
    }

    override fun onError(errorType: ErrorType) {
        _isErrorMsg.value = getResource.getString(R.string.text_error_network)
    }
}