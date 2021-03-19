package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.db.DBService
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.utilits.toDBService
import ru.int24.ownbarbershop.utilits.toDBStaff
import ru.int24.ownbarbershop.utilits.toDomService
import ru.int24.ownbarbershop.utilits.toDomStaff
import javax.inject.Inject

class UsesCaseBaseRepositoryImpl @Inject constructor(private val baseRepositoryImpl: DataBaseRepositoryImpl) : UsesCaseBaseRepository {

    override suspend fun getAllService(): MutableList<DomServices> {
        val lDBService: MutableList<DBService> = baseRepositoryImpl.getAllService()
        if (lDBService.size == 0){
            return mutableListOf<DomServices>()
        }else{
            return lDBService.mapTo(mutableListOf()){it.toDomService()}
        }
    }

    override suspend fun addService(service: DomServices) {
        baseRepositoryImpl.addService(service = service.toDBService())
    }

    override suspend fun deleteService(service: DomServices) {
        baseRepositoryImpl.deleteService(service = service.toDBService())
    }

    override suspend fun deleteServices(listServices: MutableList<DomServices>) {
        baseRepositoryImpl.deleteServices(listServices.mapTo(mutableListOf()){it.toDBService()})
    }

    override suspend fun deleteStaff(staff: DomStaff) {
        baseRepositoryImpl.deleteStaff(staff.toDBStaff())
    }

    override suspend fun addStaff(staff: DomStaff) {
       baseRepositoryImpl.addStaff(staff.toDBStaff())
    }

    override suspend fun getAllStaff(): DomStaff {
        return baseRepositoryImpl.getAllStaff()?.toDomStaff()
    }

    override suspend fun deleteAllStaff() {
        baseRepositoryImpl.deleteAllStaff()
    }
}