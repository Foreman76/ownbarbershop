package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.data.RequestAuthNet
import ru.int24.ownbarbershop.models.data.RequestRecordNet
import ru.int24.ownbarbershop.models.data.RequestSMSCodeNet
import ru.int24.ownbarbershop.models.data.RespSMSCodeNet
import ru.int24.ownbarbershop.models.domen.*
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter
import ru.int24.ownbarbershop.utilits.TypeRecord

interface NetworkRepository {

    suspend fun getServices(param: ParamForService, emitter: RemoteErrorEmitter): List<DomServices>?

    suspend fun getStaff(param: ParamForStaff, emitter: RemoteErrorEmitter): MutableList<DomStaff>?

    suspend fun getSession(param: ParamForSession, emitter: RemoteErrorEmitter): MutableList<DomSession>?

    suspend fun getWorkDays(paramForWorkDays: ParamForWorkDays, emitter: RemoteErrorEmitter): List<String>?

    suspend fun getSMSCode(paramGetSMS: ParamGetSMS, postBody:RequestSMSCodeNet, emitter: RemoteErrorEmitter): RespSMSCodeNet?

    suspend fun getAuthUser(postBody: RequestAuthNet, emitter: RemoteErrorEmitter): DomUserAuth?

    suspend fun createUserOrder(paramForRecord: ParamForRecord, postBody: RequestRecordNet, emitter: RemoteErrorEmitter): ArrayList<DomRespOrder>?

    suspend fun getUserRecords(typeRecord: TypeRecord, emitter: RemoteErrorEmitter):ArrayList<DomRecords>?
}