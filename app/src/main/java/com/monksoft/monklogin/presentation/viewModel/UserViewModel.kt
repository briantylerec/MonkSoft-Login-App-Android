package com.monksoft.monklogin.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.monksoft.monklogin.data.dataBase.UserDataBase
import com.monksoft.monklogin.data.dataBase.entities.UserEntity
import com.monksoft.monklogin.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val allUsers : LiveData<List<UserEntity>>
    private val userRepository: UserRepository

    init {
        val dao = UserDataBase.getDataBase(application).getUserDao()
        userRepository = UserRepository(dao)
        allUsers = userRepository.allUsers
    }

    fun getUser(user: String) : LiveData<UserEntity> {
        return userRepository.getUser(user)
    }

    fun insertUser(user: UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.insertUser(user)
    }

    fun updateUser(user: UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.updateUser(user)
    }

    fun deleteUser(user: String) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.deleteUser(user)
    }
}
