package ru.int24.ownbarbershop.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.ParamForService
import ru.int24.ownbarbershop.network.UseRetrofit
import ru.int24.ownbarbershop.network.getHeaders
import ru.int24.ownbarbershop.network.toDomModel
import ru.int24.ownbarbershop.utilits.NetworkUtility
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter

class NetworkRepositoryImpl(private val emitter: RemoteErrorEmitter): NetworkUtility() ,NetworkRepository {

    override suspend fun getServices(param: ParamForService): List<DomServices>? {
        val headers = getHeaders(false)

        val resp = withContext(Dispatchers.Main){ safeApiCall(emitter = emitter){ UseRetrofit.makeRetrofitAPI().getServices(headers = headers,
                staff_id = param.staff_id, companyid = param.companyid,
                datetime = param.datetime, service_ids = param.service_ids) } }
        return resp?.body()?.let { it.toDomModel()}
    }


}