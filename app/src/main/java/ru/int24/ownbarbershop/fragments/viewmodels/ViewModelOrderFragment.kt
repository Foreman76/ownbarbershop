package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.GetDataFormat
import javax.inject.Inject

class ViewModelOrderFragment @Inject constructor(private val usesCaseBaseRepositoryImpl: UsesCaseBaseRepositoryImpl): ViewModel(){


    private val listService: MutableLiveData<MutableList<DomServices>> = MutableLiveData()
    private val staff: MutableLiveData<DomStaff?> = MutableLiveData()
    private val session: MutableLiveData<String> = MutableLiveData()


    fun getSessionFromDBVM() {
        viewModelScope.launch(Dispatchers.Main) {
            usesCaseBaseRepositoryImpl.getAllSession()?.let {
                session.postValue(GetDataFormat.getUserFormatStringDateFromStringDate("yyyy-MM-dd'T'HH:mm:ssZ","dd MMMM yyyy HH:mm",it.datetime))
            } ?: ""
        }
    }

    fun getSessionFromVM(): LiveData<String> = session

    fun getServiceFromDBVM(){
        viewModelScope.launch(Dispatchers.Main) {
            listService.postValue(usesCaseBaseRepositoryImpl.getAllService())
        }
    }

    fun getServiceFromVM(): LiveData<MutableList<DomServices>> = listService

    fun getStaffFromDBVM(){
        viewModelScope.launch(Dispatchers.Main) {
            staff.postValue(usesCaseBaseRepositoryImpl.getAllStaff())
        }
    }

    fun getStaffVM():LiveData<DomStaff?> = staff

    fun deleteServices(){
        if (listService.value != null) {
            viewModelScope.launch(Dispatchers.Main) {
                usesCaseBaseRepositoryImpl.deleteServices(listService.value!!)
                listService.postValue(mutableListOf())
            }
        }
    }

    fun deleteSession(){
        session.value?.let {
            viewModelScope.launch(Dispatchers.Main) {
                usesCaseBaseRepositoryImpl.deleteAllSession()
                session.value = ""
            }
        }
    }

    fun deleteStaff(){
        staff.value?.let {
            viewModelScope.launch(Dispatchers.Main) {
                usesCaseBaseRepositoryImpl.deleteAllStaff()
                staff.value = null
            }
        }
    }

}

