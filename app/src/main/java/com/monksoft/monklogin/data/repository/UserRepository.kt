package com.monksoft.monklogin.data.repository

import androidx.lifecycle.LiveData
import com.monksoft.monklogin.data.dataBase.dao.UserDao
import com.monksoft.monklogin.data.dataBase.entities.UserEntity

class UserRepository ( private val userDao : UserDao ) {

    val allUsers: LiveData<List<UserEntity>> = userDao.getAllUsers()

    fun getUser(user: String): LiveData<UserEntity> = userDao.getUser(user)

    suspend fun insertUser(user : UserEntity) = userDao.insertUser(user)

    suspend fun updateUser(user : UserEntity) = userDao.updateUser(user)

    suspend fun deleteUser(user: String) = userDao.deleteUser(user)
}