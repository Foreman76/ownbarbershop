package ru.int24.ownbarbershop.dao

import androidx.room.Dao
import androidx.room.Query
import ru.int24.ownbarbershop.models.db.DBService


@Dao
interface BaseDao {

    @Query("Select * from service")
    suspend fun getAllService(): MutableList<DBService>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = DBDocument::class)
//    suspend fun addDocument(doc:DBDocument)
//
//    @Delete(entity = DBDocument::class)
//    suspend fun deleteDocument(doc:DBDocument)


}