package ru.int24.ownbarbershop.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class DBSettings(
        @PrimaryKey(autoGenerate = false)
        val id:Int = 0,
        val phone: String = "",
        val smsCode: String = "",
        val userName: String = "",
        val userToken: String = "",
        val userEmail: String = "",
        val userPassword: String = "",
        val avatar:String = "",
        val user_id:String = ""

)
