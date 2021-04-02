package ru.int24.ownbarbershop.repositories

import ru.int24.ownbarbershop.models.db.DBService
import ru.int24.ownbarbershop.models.db.DBSession
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.utilits.*
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



    //Работаем с сессиями
    override suspend fun addSession(session: DBSession) {
        baseRepositoryImpl.addSession(session)
    }

    override suspend fun deleteAllSession() {
       baseRepositoryImpl.deleteAllSession()
    }

    override suspend fun getAllSession(): String {
        val dbSession = baseRepositoryImpl.getAllSession()
        return if (dbSession != null){
            GetDataFormat.getUserFormatStringDateFromStringDate("yyyy-MM-dd'T'HH:mm:ssZ","dd MMMM yyyy hh:mm",dbSession.datetime)
        } else ""
    }

}