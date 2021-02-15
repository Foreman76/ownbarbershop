package ru.int24.ownbarbershop.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.ParamForService
import ru.int24.ownbarbershop.models.netresult.NetResult
import ru.int24.ownbarbershop.network.UseRetrofit
import ru.int24.ownbarbershop.network.getHeaders
import ru.int24.ownbarbershop.network.toDomModel
import java.io.IOException

class NetworkRepositoryImpl(): NetworkRepository {
    override suspend fun getServices(param: ParamForService): NetResult<List<DomServices>> {
        throw UninitializedPropertyAccessException()
    }

//    override suspend fun getServices(param: ParamForService): NetResult<List<DomServices>> {
//
//        val headers = getHeaders(false)
//
//        val serviceResponse  = safeApiCall {
//            UseRetrofit.makeRetrofitAPI().getServices(headers = headers,
//                        staff_id = param.staff_id, companyid = param.companyid,
//                        datetime = param.datetime, service_ids = param.service_ids)
//        }
//
//        return when (serviceResponse) {
//            is NetResult.Success -> NetResult.Success(serviceResponse.value.body()?.toDomModel() ?: throw NullPointerException())
//            is NetResult.GenericError -> NetResult.GenericError(code = serviceResponse.code, error = serviceResponse.error)
//            is NetResult.NetworkError -> NetResult.NetworkError
//        }
//    }

    override suspend fun getServicesTest(param: ParamForService): NetResult<List<DomServices>> {
        val headers = getHeaders(false)
        try {
            val resp = withContext(Dispatchers.IO){ UseRetrofit.makeRetrofitAPI().getServices(headers = headers,
                    staff_id = param.staff_id, companyid = param.companyid,
                    datetime = param.datetime, service_ids = param.service_ids)}
            return NetResult.Success(resp.body()?.toDomModel() ?: listOf())

        } catch (throwable: Throwable) {
            when(throwable){
                is IOException -> return NetResult.NetworkError
                is HttpException -> return NetResult.GenericError(throwable.code() , "Network error")
                else -> return NetResult.GenericError(null, "Unknown network error")
            }
        }
    }

}