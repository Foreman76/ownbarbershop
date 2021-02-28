package ru.int24.ownbarbershop.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.ParamForService
import ru.int24.ownbarbershop.network.ApiYclients
import ru.int24.ownbarbershop.network.getHeaders
import ru.int24.ownbarbershop.network.toDomModel
import ru.int24.ownbarbershop.utilits.NetworkUtility
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: ApiYclients): NetworkUtility() ,NetworkRepository{

//    init {
//        App.appComponent.inject(this@NetworkRepositoryImpl)
//    }

//    @Inject lateinit var api: ApiYclients

    override suspend fun getServices(param: ParamForService, emitter: RemoteErrorEmitter): MutableList<DomServices>? {
        val headers = getHeaders(false)

        val resp = withContext(Dispatchers.Main){ safeApiCall(emitter = emitter){ api.getServices(headers = headers,
                staff_id = param.staff_id, companyid = param.companyid,
                datetime = param.datetime, service_ids = param.service_ids) } }
        return resp?.body()?.let { it.toDomModel()}
    }

}