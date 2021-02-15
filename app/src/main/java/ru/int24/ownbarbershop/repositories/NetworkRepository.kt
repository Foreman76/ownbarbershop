package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.ParamForService

interface NetworkRepository {

    suspend fun getServices(param: ParamForService): List<DomServices>?

}