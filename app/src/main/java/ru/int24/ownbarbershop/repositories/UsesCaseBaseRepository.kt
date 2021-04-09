package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.db.DBSession
import ru.int24.ownbarbershop.models.db.DBSettings
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomSession
import ru.int24.ownbarbershop.models.domen.DomSettings
import ru.int24.ownbarbershop.models.domen.DomStaff

interface UsesCaseBaseRepository {

    suspend fun getAllService(): MutableList<DomServices>

    suspend fun addService(service: DomServices)

    suspend fun deleteService(service: DomServices)

    suspend fun deleteServices(listServices: MutableList<DomServices>)

    // Работаем с сотрудником
    suspend fun deleteStaff(staff: DomStaff)

    suspend fun addStaff(staff: DomStaff)

    suspend fun getAllStaff(): DomStaff?

    suspend fun deleteAllStaff()

//    Блок сеансов

    suspend fun addSession(session: DBSession)

    suspend fun deleteAllSession()

    suspend fun getAllSession(): DomSession?

//    Блок настроек

    suspend fun getAllSettings(): DomSettings?

    suspend fun addSettings(dbSettings: DBSettings)

    suspend fun deleteAllSettings()
}