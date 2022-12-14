package com.monksoft.monklogin.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.monksoft.monklogin.data.dataBase.dao.UserDao
import com.monksoft.monklogin.data.dataBase.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun getUserDao() : UserDao

    companion object {
        private var INSTANCE : UserDataBase? = null

        fun getDataBase(context: Context): UserDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "user_database").build()
                INSTANCE =  instance
                instance
            }
        }
    }
}