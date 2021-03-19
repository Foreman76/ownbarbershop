package ru.int24.ownbarbershop.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.models.domen.ParamForService
import ru.int24.ownbarbershop.models.domen.ParamForStaff
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

}