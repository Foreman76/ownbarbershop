package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.netresult.NetResult

interface NetworkRepository {

    suspend fun getServices(data: Map<String, Any?>): NetResult<List<DomServices>>

}