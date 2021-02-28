package ru.int24.ownbarbershop.repositories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.int24.ownbarbershop.dao.BaseDao
import ru.int24.ownbarbershop.models.db.DBService


@Database(entities = [DBService::class], version = 1, exportSchema = false)
abstract class UseDataBase: RoomDatabase() {

    abstract fun documentDao(): BaseDao

    companion object {
        @Volatile
        private var INSTANCE: UseDataBase? = null

        fun getInstance(context: Context): UseDataBase{

            val templInstance = INSTANCE

            if (templInstance != null) return templInstance

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, UseDataBase::class.java, "use_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}