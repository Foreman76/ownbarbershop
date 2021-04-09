package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.models.db.DBSession
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomSession
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.*
import javax.inject.Inject

class VMDateTimeFragment @Inject constructor(private val usesCaseBaseRepositoryImpl: UsesCaseBaseRepositoryImpl,
                                             private val networkRepositoryImpl: NetworkRepositoryImpl,
                                             private val getResource: GetResource) : ViewModel(), RemoteErrorEmitter {


    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isErrorMessage: MutableLiveData<String> = MutableLiveData()
    val isErrorMessage: LiveData<String> = _isErrorMessage
    private val listSessionTime: MutableLiveData<MutableList<DomSession>?> = MutableLiveData()
    private val listWorkDays: MutableLiveData<List<String>> = MutableLiveData()



    fun getListWorkDaysVM(): LiveData<List<String>> = listWorkDays
    fun getListSessionTimeVM(): LiveData<MutableList<DomSession>?> = listSessionTime

    fun getAvailableWorkdaysFromNet(){
        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true
            val param = RequestParameters.makeParametersForWorkDays(staff = usesCaseBaseRepositoryImpl.getAllStaff(),
            listService = usesCaseBaseRepositoryImpl.getAllService())
            listWorkDays.postValue(networkRepositoryImpl.getWorkDays(param,this@VMDateTimeFragment))
            _isLoading.value = false
        }
    }

    fun getAvailableSessionsFromNet(date: Long?) {

        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true
            val stringDate = GetDataFormat.getDateBaseISOFormat(date!!)
            val domStaff: DomStaff? = usesCaseBaseRepositoryImpl.getAllStaff()
            val listService:MutableList<DomServices> = usesCaseBaseRepositoryImpl.getAllService()
            val param = RequestParameters.makeParametersForSession(sessionDate = stringDate,
                                                    staff = domStaff, listService = listService)
            listSessionTime.postValue(networkRepositoryImpl.getSession(param, this@VMDateTimeFragment))

            _isLoading.value = false
        }
    }


    fun addSesionVM(stringDateTime: String) {
        val dbSession = DBSession(datetime = stringDateTime)
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