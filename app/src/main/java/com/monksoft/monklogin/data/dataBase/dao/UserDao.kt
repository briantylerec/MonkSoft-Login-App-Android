package com.monksoft.monklogin.data.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.monksoft.monklogin.data.dataBase.entities.UserEntity

@Dao
interface UserDao {
    @Query("select * from user_table")
    fun getAllUsers() : LiveData<List<UserEntity>>

    @Query("select * from user_table where user_name= :user")
    fun getUser(user : String) : LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : UserEntity)

    @Update
    suspend fun updateUser(user : UserEntity)

    @Query("delete from user_table where user_name = :user")
    suspend fun deleteUser(user : String)
}