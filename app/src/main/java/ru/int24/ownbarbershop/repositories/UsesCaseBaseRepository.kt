package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomStaff

interface UsesCaseBaseRepository {

    suspend fun getAllService(): MutableList<DomServices>

    suspend fun addService(service: DomServices)

    suspend fun deleteService(service: DomServices)

    suspend fun deleteServices(listServices: MutableList<DomServices>)

    // Работаем с сотрудником
    suspend fun deleteStaff(staff: DomStaff)

    suspend fun addStaff(staff: DomStaff)

    suspend fun getAllStaff(): DomStaff

    suspend fun deleteAllStaff()
}