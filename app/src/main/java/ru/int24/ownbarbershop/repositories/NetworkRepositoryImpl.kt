package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.domen.DomResult
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.ParamForService
import ru.int24.ownbarbershop.models.netresult.NetResult
import ru.int24.ownbarbershop.network.UseRetrofit
import ru.int24.ownbarbershop.network.getHeaders
import ru.int24.ownbarbershop.network.safeApiCall
import ru.int24.ownbarbershop.network.toDomModel

class NetworkRepositoryImpl(): NetworkRepository {

    override suspend fun getServices(param: ParamForService): NetResult<List<DomServices>> {

        val headers = getHeaders(false)

        val serviceResponse  = safeApiCall { UseRetrofit.makeRetrofitAPI().getServices(headers = headers,
                        staff_id = param.staff_id, companyid = param.companyid,
                        datetime = param.datetime, service_ids = param.service_ids)}

        return serviceResponse.

    }

}