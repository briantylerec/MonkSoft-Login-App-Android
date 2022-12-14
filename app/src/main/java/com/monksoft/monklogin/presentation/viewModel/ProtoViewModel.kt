package com.monksoft.monklogin.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.monksoft.monklogin.data.repository.ProtoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProtoViewModel(application: Application) : AndroidViewModel(application) {

    private val protoRepository = ProtoRepository(application)
    val isLoading = MutableLiveData<Boolean>()
    val ekuCode = protoRepository.readProto.asLiveData()

    fun saveEkucode(ekucode: String) = viewModelScope.launch(Dispatchers.IO) {
        isLoading.postValue(true)
        protoRepository.writeProto(ekucode)
        isLoading.postValue(false)
    }
}