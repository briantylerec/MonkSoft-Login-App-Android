package com.monksoft.monklogin.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class Preferences ( context : Context ) {

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    private val securePreferences = EncryptedSharedPreferences.create(
        Constants.MAP_PREFERENCES_KEY,
        mainKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun addValue(value: String) = securePreferences.edit().putString(Constants.PREFERENCES_KEY, value).commit()

    fun getValue() : String? = securePreferences.getString(Constants.PREFERENCES_KEY, null)

    fun clear(): Any = securePreferences.edit().clear()
}