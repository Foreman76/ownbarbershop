package ru.int24.ownbarbershop.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.int24.ownbarbershop.dao.UseDataBase
import ru.int24.ownbarbershop.models.db.DBService
import ru.int24.ownbarbershop.models.db.DBSession
import ru.int24.ownbarbershop.models.db.DBSettings
import ru.int24.ownbarbershop.models.db.DBStaff
import javax.inject.Inject

class DataBaseRepositoryImpl @Inject constructor(private val useDataBase: UseDataBase): BaseRepository{

    private val baseDao = useDataBase.baseDao()

    override suspend fun getAllService(): MutableList<DBService> {
        return withContext(Dispatchers.IO){ baseDao.getAllService() }
    }

    override suspend fun addService(service: DBService) {
        withContext(Dispatchers.IO){ baseDao.addService(service)}
    }

    override suspend fun deleteService(service: DBService) {
        withContext(Dispatchers.IO){ baseDao.deleteService(service) }
    }

    override suspend fun deleteServices(listServices: MutableList<DBService>) {
        withContext(Dispatchers.IO){ baseDao.deleteServices(listServices)}
    }

    override suspend fun deleteStaff(staff: DBStaff) {
        withContext(Dispatchers.IO) {baseDao.deleteStaff(staff)}
    }

    override suspend fun addStaff(staff: DBStaff) {
       withContext(Dispatchers.IO) {baseDao.addStaff(staff)}
    }

    override suspend fun getAllStaff(): DBStaff? {
        return withContext(Dispatchers.IO) {baseDao.getAllStaff()}
    }

    override suspend fun deleteAllStaff() {
        withContext(Dispatchers.IO) {baseDao.deleteAllStaff()}
    }

    override suspend fun addSession(session: DBSession) {
        withContext(Dispatchers.IO) {baseDao.addSession(session)}
    }

    override suspend fun deleteAllSession() {
        withContext(Dispatchers.IO) {baseDao.deleteAllSession()}
    }

    override suspend fun getAllSession(): DBSession? {
        return withContext(Dispatchers.IO) {baseDao.getAllSession()}
    }

    override suspend fun getAllSettings(): DBSettings? {
        return withContext(Dispatchers.IO){baseDao.getAllSettings()}
    }

    override suspend fun addSettings(dbSettings: DBSettings) {
        withContext(Dispatchers.IO){baseDao.addSettings(dbSettings)}
    }

    override suspend fun deleteAllSettings() {
        withContext(Dispatchers.IO){baseDao.deleteAllSettings()}
    }


}


