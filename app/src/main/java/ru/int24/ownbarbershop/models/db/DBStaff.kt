package ru.int24.ownbarbershop.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "staff")
data class DBStaff(
        @PrimaryKey(autoGenerate = false)
        val id : Int = 0,
        val api_id : String ="",
        val name : String = "",
        val specialization:String = "",
        val rating : Int = 0,
        val show_rating : Int = 0,
        val avatar : String = "",
        val avatar_big : String = "",
        val comments_count : Int = 0,
        val votes_count : Int = 0,
        val bookable : Boolean = false,
        val information : String = "",
        val position_id : Int = 0,
        val schedule_till : String = "",
        val weight : Int = 0,
        val fired : Int = 0,
        val status : Int = 0,
        val hidden : Int = 0,
        val user : String = "",
        val prepaid : String = ""



)