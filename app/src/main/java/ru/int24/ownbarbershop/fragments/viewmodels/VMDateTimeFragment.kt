package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.models.db.DBSession
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.ErrorType
import ru.int24.ownbarbershop.utilits.GetDataFormat
import ru.int24.ownbarbershop.utilits.GetResource
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter
import javax.inject.Inject

class VMDateTimeFragment @Inject constructor(private val usesCaseBaseRepositoryImpl: UsesCaseBaseRepositoryImpl,
                                             private val networkRepositoryImpl: NetworkRepositoryImpl,
                                             private val getResource: GetResource): ViewModel(), RemoteErrorEmitter  {


    private val _isLoading:MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isErrorMessage:MutableLiveData<String> = MutableLiveData()
    val isErrorMessage: LiveData<String> = _isErrorMessage

    fun getAvailableSessionsFromNet(){
        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true


            _isLoading.value = false
        }
    }


    fun addSesionVM(date:Long?){
        val stringDate = GetDataFormat.getDateBaseISOFormat(date!!)
        val dbSession = DBSession(datetime = stringDate)
        viewModelScope.launch(Dispatchers.Main) {
            usesCaseBaseRepositoryImpl.addSession(dbSession)
        }
    }

    override fun onError(msg: String) {
        _isErrorMessage.value = msg
    }

    override fun onError(errorType: ErrorType) {
        _isErrorMessage.value = getResource.getString(R.string.text_error_network)
    }


}