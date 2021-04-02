package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.db.DBService
import ru.int24.ownbarbershop.models.db.DBSession
import ru.int24.ownbarbershop.models.db.DBStaff

interface BaseRepository {

    suspend fun getAllService(): MutableList<DBService>

    suspend fun addService(service:DBService)

    suspend fun deleteService(service:DBService)

    suspend fun deleteServices(listServices: MutableList<DBService>)

    // Работаем с сотрудником
    suspend fun deleteStaff(staff: DBStaff)

    suspend fun addStaff(staff: DBStaff)

    suspend fun getAllStaff(): DBStaff

    suspend fun deleteAllStaff()

    //  Блок сеансы

    suspend fun addSession(session: DBSession)

    suspend fun deleteAllSession()

    suspend fun getAllSession(): DBSession

}