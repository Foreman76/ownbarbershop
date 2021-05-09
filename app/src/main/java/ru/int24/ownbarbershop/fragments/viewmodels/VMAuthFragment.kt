package ru.int24.ownbarbershop.fragments.viewmodels

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.data.RequestAuthNet
import ru.int24.ownbarbershop.models.data.RequestSMSCodeNet
import ru.int24.ownbarbershop.models.db.DBSettings
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.*
import javax.inject.Inject

class VMAuthFragment @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl,
                                         private val usesCaseBaseRepositoryImpl: UsesCaseBaseRepositoryImpl,
                                         private val getResource: GetResource):ViewModel(), RemoteErrorEmitter {

    private val _isGetSMSCode: MutableLiveData<Boolean> = MutableLiveData()
    val isGetSMSCode:LiveData<Boolean> = _isGetSMSCode
    private val _isErrorMessage: MutableLiveData<String> = MutableLiveData()
    private val _isPhone: MutableLiveData<String> = MutableLiveData()
    private val _isName: MutableLiveData<String> = MutableLiveData()
    private val _isTimerValue: MutableLiveData<String> = MutableLiveData()
    private val _isUserToken:MutableLiveData<Boolean> = MutableLiveData()
    val isUserToken: LiveData<Boolean> = _isUserToken
    val isPhone: LiveData<String> = _isPhone
    val isName: LiveData<String> = _isName
    val isErrorMessage: LiveData<String> = _isErrorMessage
    val isTimerValue: LiveData<String> = _isTimerValue


    fun getSettingsFromDB(){
        viewModelScope.launch(Dispatchers.Main) {
            val settings = usesCaseBaseRepositoryImpl.getAllSettings()
            settings?.let {
                _isName.value = it.userName
                _isPhone.value = it.phone
            }
        }
    }


    fun getTimerValue(){

        viewModelScope.launch {
            val countDownTimer = object : CountDownTimer(60000, 1000){
                override fun onTick(millisUntilFinished: Long) {
                    _isTimerValue.value = (millisUntilFinished / 1000).toString()
                }

                override fun onFinish() {

                }
            }
            countDownTimer.start()
        }

    }


    fun getSMSCodeFromNet(phone:String, name:String){
        viewModelScope.launch(Dispatchers.Main) {
//            в базу запишем номер без префикса +7
            val dbSettings = DBSettings(phone = phone, userName = name )
            val requestSMSCodeNet = RequestSMSCodeNet(phone= phone.padStart(11, DefConfig.phone_prefix), name)
            val param = RequestParameters.makeParametersForGetSMSCode()
            usesCaseBaseRepositoryImpl.addSettings(dbSettings)
            val resp = networkRepositoryImpl.getSMSCode(paramGetSMS = param,
                    postBody = requestSMSCodeNet, this@VMAuthFragment)
            _isGetSMSCode.value = resp?.success
        }
    }

    fun getAuthUserFromNet(phone: String, code: String){
        viewModelScope.launch(Dispatchers.Main) {
            val requestAuthNet = RequestAuthNet(phone= phone.padStart(11, DefConfig.phone_prefix),code=code)
            val resp = networkRepositoryImpl.getAuthUser(postBody = requestAuthNet, emitter = this@VMAuthFragment)
            resp?.let {domAuthUser ->

                val domSettings = usesCaseBaseRepositoryImpl.getAllSettings()
                domSettings?.let {domSettings ->
                    domSettings.userToken = domAuthUser.user_token
                    domSettings.smsCode = code
                    domSettings.userEmail = domAuthUser.email
                    domSettings.user_id = domAuthUser.user_id
                    domSettings.avatar = domAuthUser.avatar
                    usesCaseBaseRepositoryImpl.addSettings(domSettings.toDBSettings())

                updateDefConfig(domSettings)

                _isUserToken.value = true
                }
            }
        }
    }

    override fun onError(msg: String) {
        _isErrorMessage.value = msg
    }

    override fun onError(errorType: ErrorType) {
        _isErrorMessage.value = getResource.getString(R.string.text_error_network)
    }


}