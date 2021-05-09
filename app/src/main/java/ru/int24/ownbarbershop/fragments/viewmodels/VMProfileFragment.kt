package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.models.domen.DomSettings
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.GetResource
import ru.int24.ownbarbershop.utilits.toDBSettings
import ru.int24.ownbarbershop.utilits.updateDefConfig
import javax.inject.Inject

class VMProfileFragment @Inject constructor(private val usesCaseBaseRepositoryImpl: UsesCaseBaseRepositoryImpl,
                                            private val getResource: GetResource): ViewModel() {

    private val _isSettings: MutableLiveData<DomSettings?> = MutableLiveData()
    var isSettings: LiveData<DomSettings?> = _isSettings
    private val _isDeleteSettings: MutableLiveData<Boolean> = MutableLiveData()
    var isDeleteSettings: LiveData<Boolean> = _isDeleteSettings


    fun getSettingsFromDBVM(){
        viewModelScope.launch(Dispatchers.Main) {
            _isSettings.postValue(usesCaseBaseRepositoryImpl.getAllSettings())
        }
    }

    fun updateSettingsToDBVM(){
        viewModelScope.launch(Dispatchers.Main) {
            val domSettings = usesCaseBaseRepositoryImpl.getAllSettings()
            domSettings?.let {
                it.userToken = ""
                usesCaseBaseRepositoryImpl.addSettings(it.toDBSettings())

                updateDefConfig(domSettings)
                _isDeleteSettings.value = true

            }
        }
    }

}