package ru.int24.ownbarbershop.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "service")
data class DBService (
        @PrimaryKey(autoGenerate = false)
        val id : Int = 0,
        val title : String = "",
        val category_id : Int = 0,
        val price_min : Int = 0,
        val price_max : Int = 0,
        val discount : Int = 0,
        val comment : String = "",
        val weight : Int = 0,
        val active : Int = 0,
        val sex : Int = 0,
        val image : String = "",
        val prepaid : String = "",
        val seance_length : Int = 0,
        val position: Int = 0,
        val type_card: String = ""

)
