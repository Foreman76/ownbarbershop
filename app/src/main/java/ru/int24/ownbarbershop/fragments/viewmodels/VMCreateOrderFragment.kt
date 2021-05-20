package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.data.OrderAppointments
import ru.int24.ownbarbershop.models.data.RequestRecordNet
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomSession
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.models.domen.ParamForRecord
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.ErrorType
import ru.int24.ownbarbershop.utilits.GetResource
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter
import javax.inject.Inject

class VMCreateOrderFragment @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl,
                                                private val usesCaseBaseRepositoryImpl: UsesCaseBaseRepositoryImpl,
                                                private val getResource: GetResource): ViewModel(), RemoteErrorEmitter {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading:LiveData<Boolean> = _isLoading
    private val _isErrorMsg: MutableLiveData<String> = MutableLiveData()
    val isErrorMsg:LiveData<String> = _isErrorMsg

    private val _isErrorOrder: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorOrder: LiveData<Boolean> = _isErrorOrder



    fun createOrderVM(){

        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true

            //Собираем данные для request из базы, данные будут всегда
            val requestStaff = usesCaseBaseRepositoryImpl.getAllStaff()
            val requestService = usesCaseBaseRepositoryImpl.getAllService()
            val requestSession = usesCaseBaseRepositoryImpl.getAllSession()
            val requestSettings = usesCaseBaseRepositoryImpl.getAllSettings()
            val requestRecordNet = RequestRecordNet(phone = requestSettings!!.phone,
                                    fullname = requestSettings!!.userName,
                                    email = requestSettings!!.userEmail,
                                    appointments = getAappointments(requestStaff, requestSession, getListIdSevice(requestService)),
                                    code = requestSettings.smsCode,
                                    notify_by_sms = 1)
            val resp = networkRepositoryImpl.createUserOrder(ParamForRecord(companyid = DefConfig.id), requestRecordNet, this@VMCreateOrderFragment)
            if (resp.isNullOrEmpty()){
                _isErrorOrder.value = true
            }else {
                _isErrorOrder.value = false
                usesCaseBaseRepositoryImpl.deleteAllSession()
                usesCaseBaseRepositoryImpl.deleteAllStaff()
                usesCaseBaseRepositoryImpl.deleteServices(requestService)
            }
            _isLoading.value = false
        }
    }

    private fun getAappointments(domStaff:DomStaff?, domSession: DomSession?, listIdService: ArrayList<Int>): ArrayList<OrderAppointments> {
        val listOrder = ArrayList<OrderAppointments>()

        listOrder.add(OrderAppointments(
                id = 1,
                services = listIdService,
                staff_id = domStaff!!.id,
                datetime = domSession!!.datetime
        ))

        return listOrder
    }

    private fun getListIdSevice(listServices: MutableList<DomServices>?): ArrayList<Int> {
        return listServices!!.mapTo(arrayListOf(),{it.id})
    }

    override fun onError(msg: String) {
        _isErrorMsg.value = msg
    }

    override fun onError(errorType: ErrorType) {
       _isErrorMsg.value = getResource.getString(R.string.text_error_network)
    }

}