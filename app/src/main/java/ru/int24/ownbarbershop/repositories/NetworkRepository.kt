package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.models.domen.ParamForService
import ru.int24.ownbarbershop.models.domen.ParamForStaff
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter

interface NetworkRepository {

    suspend fun getServices(param: ParamForService, emitter: RemoteErrorEmitter): List<DomServices>?

    suspend fun getStaff(param: ParamForStaff, emitter: RemoteErrorEmitter): MutableList<DomStaff>?

}