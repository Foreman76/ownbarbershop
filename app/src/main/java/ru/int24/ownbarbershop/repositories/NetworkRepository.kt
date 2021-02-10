package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.ParamForService
import ru.int24.ownbarbershop.models.netresult.NetResult

interface NetworkRepository {

    suspend fun getServices(param: ParamForService): NetResult<List<DomServices>>

}