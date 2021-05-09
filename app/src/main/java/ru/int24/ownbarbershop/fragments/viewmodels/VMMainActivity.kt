package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.models.domen.DomSettings
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import javax.inject.Inject

class VMMainActivity @Inject constructor(private val usesCaseBaseRepositoryImpl: UsesCaseBaseRepositoryImpl): ViewModel() {

    private val _isSettingsFromDB: MutableLiveData<DomSettings?> = MutableLiveData()


    fun getSettingsFromDB(){
        viewModelScope.launch(Dispatchers.Main) {
            _isSettingsFromDB.postValue(usesCaseBaseRepositoryImpl.getAllSettings())
        }
    }

    fun getSettingsFromVM(): LiveData<DomSettings?> = _isSettingsFromDB


}