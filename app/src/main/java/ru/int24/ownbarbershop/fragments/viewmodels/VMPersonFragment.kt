package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.models.domen.DomSettings
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.toDBSettings
import javax.inject.Inject

class VMPersonFragment @Inject constructor(private val usesCaseBaseRepositoryImpl: UsesCaseBaseRepositoryImpl): ViewModel() {


    private val _isSettings: MutableLiveData<DomSettings?> = MutableLiveData()
    private val _isSaveToBase: MutableLiveData<Boolean> = MutableLiveData()
    val isSaveTobase: LiveData<Boolean> = _isSaveToBase

    fun getSettingsFromDB(){
        viewModelScope.launch(Dispatchers.Main) {
            _isSettings.postValue(usesCaseBaseRepositoryImpl.getAllSettings())
        }
    }

    fun getSettingsVM():LiveData<DomSettings?> = _isSettings

    fun saveSettingsToDB(domSettings: DomSettings){
        viewModelScope.launch(Dispatchers.Main) {
            usesCaseBaseRepositoryImpl.addSettings(domSettings.toDBSettings())
            _isSaveToBase.value = true
        }
    }


}