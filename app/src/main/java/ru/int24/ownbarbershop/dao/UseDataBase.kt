package ru.int24.ownbarbershop.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.int24.ownbarbershop.models.db.DBService
import ru.int24.ownbarbershop.models.db.DBSession
import ru.int24.ownbarbershop.models.db.DBStaff


@Database(entities = [DBService::class, DBStaff::class, DBSession::class], version = 3, exportSchema = false)
abstract class UseDataBase : RoomDatabase() {

    abstract fun baseDao(): BaseDao
}