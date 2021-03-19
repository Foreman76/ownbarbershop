package ru.int24.ownbarbershop.dao

import androidx.room.*
import ru.int24.ownbarbershop.models.db.DBService
import ru.int24.ownbarbershop.models.db.DBStaff


@Dao
interface BaseDao {

    @Query("Select * from service")
    suspend fun getAllService(): MutableList<DBService>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = DBService::class)
    suspend fun addService(service: DBService)

    @Delete(entity = DBService::class)
    suspend fun deleteService(service:DBService)

    @Delete(entity = DBService::class)
    suspend fun deleteServices(listServices: MutableList<DBService>)

//    Блок сотрудники

    @Insert(entity = DBStaff::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStaff(staff:DBStaff)

    @Delete(entity = DBStaff::class)
    suspend fun deleteStaff(staff: DBStaff)

    @Query("Select * from staff")
    suspend fun getAllStaff(): DBStaff    // В базе всегда только один сотрудник

    @Query("DELETE  from staff")
    suspend fun deleteAllStaff()
}