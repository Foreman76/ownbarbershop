package ru.int24.ownbarbershop.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.int24.ownbarbershop.models.db.DBService
import ru.int24.ownbarbershop.models.db.DBStaff


@Database(entities = [DBService::class, DBStaff::class], version = 2, exportSchema = false)
abstract class UseDataBase : RoomDatabase() {

    abstract fun baseDao(): BaseDao
}