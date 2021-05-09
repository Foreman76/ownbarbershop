package ru.int24.ownbarbershop.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.int24.ownbarbershop.models.data.RequestAuthNet
import ru.int24.ownbarbershop.models.data.RequestRecordNet
import ru.int24.ownbarbershop.models.data.RequestSMSCodeNet
import ru.int24.ownbarbershop.models.data.RespSMSCodeNet
import ru.int24.ownbarbershop.models.domen.*
import ru.int24.ownbarbershop.network.ApiYclients
import ru.int24.ownbarbershop.network.getHeaders
import ru.int24.ownbarbershop.utilits.NetworkUtility
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter
import ru.int24.ownbarbershop.utilits.toDomModel
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: ApiYclients): NetworkUtility() ,NetworkRepository{


    override suspend fun getServices(param: ParamForService, emitter: RemoteErrorEmitter): MutableList<DomServices>? {
        val headers = getHeaders(false)

        val resp = withContext(Dispatchers.Main){ safeApiCall(emitter = emitter){ api.getServices(headers = headers,
                staff_id = param.staff_id, companyid = param.companyid,
                datetime = param.datetime, service_ids = param.service_ids) } }
        return resp?.body()?.toDomModel()
    }

    override suspend fun getStaff(param: ParamForStaff, emitter: RemoteErrorEmitter): MutableList<DomStaff>? {
        val headers = getHeaders(false)
        val resp = withContext(Dispatchers.Main) {safeApiCall(emitter=emitter){api.getStaff(headers = headers,
                companyid = param.companyid,
        datetime = param.datetime, service_ids = param.service_ids)} }
        return resp?.body()?.toDomModel()
    }

    override suspend fun getSession(param: ParamForSession, emitter: RemoteErrorEmitter): MutableList<DomSession>? {
        val headers = getHeaders(false)
        val resp = withContext(Dispatchers.Main){safeApiCall(emitter=emitter){api.getSession(headers=headers,
            companyid = param.companyid, staff_id = param.staff_id, datetime = param.datetime, service_ids = param.service_ids)} }
        return resp?.body()?.toDomModel()
    }

    override suspend fun getWorkDays(paramForWorkDays: ParamForWorkDays, emitter: RemoteErrorEmitter): List<String>? {
        val headers = getHeaders(false)
        val resp = withContext(Dispatchers.Main){safeApiCall(emitter=emitter){api.getWorkDays(headers = headers,
        companyid = paramForWorkDays.companyid, staff_id = paramForWorkDays.staff_id, service_ids = paramForWorkDays.service_ids)} }
        return resp?.body()?.toDomModel()
    }

    override suspend fun getSMSCode(paramGetSMS: ParamGetSMS, postBody: RequestSMSCodeNet, emitter: RemoteErrorEmitter):RespSMSCodeNet? {
        val headers = getHeaders(false)
        val resp = withContext(Dispatchers.Main){safeApiCall(emitter = emitter){api.getSMSCode(headers = headers, companyid = paramGetSMS.companyid,
        requestSMSCode = postBody)} }
        return resp?.body()
    }

    override suspend fun getAuthUser(postBody: RequestAuthNet, emitter: RemoteErrorEmitter): DomUserAuth? {
        val headers = getHeaders(false)
        val resp = withContext(Dispatchers.Main){safeApiCall(emitter = emitter){api.getAuthUser(headers = headers, requestAuth = postBody)} }
        return resp?.body()?.toDomModel()
    }

    override suspend fun createUserOrder(
        paramForRecord: ParamForRecord,
        postBody: RequestRecordNet,
        emitter: RemoteErrorEmitter
    ): ArrayList<DomRespOrder>? {
        val headers = getHeaders(false)
        val resp = withContext(Dispatchers.Main){safeApiCall(emitter = emitter){api.createUserOrder(headers = headers, companyid = paramForRecord.companyid, requestRecord = postBody)} }
        return resp?.body()?.toDomModel()
    }

    override suspend fun getUserRecords(emitter: RemoteErrorEmitter): ArrayList<DomRecords>? {
        val headers = getHeaders(true)
        val resp = withContext(Dispatchers.Main){safeApiCall(emitter = emitter){api.getUserRecords(headers = headers)} }
        return resp?.body()?.mapTo(arrayListOf(), {it.toDomModel()})
    }

}