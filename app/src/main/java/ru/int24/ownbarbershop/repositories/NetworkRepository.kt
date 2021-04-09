package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.domen.*
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter

interface NetworkRepository {

    suspend fun getServices(param: ParamForService, emitter: RemoteErrorEmitter): List<DomServices>?

    suspend fun getStaff(param: ParamForStaff, emitter: RemoteErrorEmitter): MutableList<DomStaff>?

    suspend fun getSession(param: ParamForSession, emitter: RemoteErrorEmitter): MutableList<DomSession>?

    suspend fun getWorkDays(paramForWorkDays: ParamForWorkDays, emitter: RemoteErrorEmitter): List<String>?

}