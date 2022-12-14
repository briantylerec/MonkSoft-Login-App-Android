package com.monksoft.monklogin.data.dataBase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity (

    @PrimaryKey
    @ColumnInfo(name ="user_name")
    val username : String = "",

    @ColumnInfo(name ="intent")
    val intent: Int = 0,
)