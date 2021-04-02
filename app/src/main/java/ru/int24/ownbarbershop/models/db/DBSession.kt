package ru.int24.ownbarbershop.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data class DBSession(
        @PrimaryKey(autoGenerate = false)
        val datetime: String = ""

)
