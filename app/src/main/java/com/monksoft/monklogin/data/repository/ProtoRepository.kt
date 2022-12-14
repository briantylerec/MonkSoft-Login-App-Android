package com.monksoft.monklogin.data.repository

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import com.monksoft.monklogin.Monk
import com.monksoft.monklogin.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class ProtoRepository(context: Context) {

    private val dataStore: DataStore<Monk> = context.createDataStore(
        Constants.BUFFER_FILE_NAME,
        serializer = ProtoSerializer()
    )

    val readProto: Flow<Monk> = dataStore.data
        .catch { e ->
            if(e is IOException) emit(Monk.getDefaultInstance())
            else throw e
        }

    suspend fun writeProto(ekucode: String){
        dataStore.updateData { data ->
            data.toBuilder().setMonkcode(ekucode).build()
        }
    }
}