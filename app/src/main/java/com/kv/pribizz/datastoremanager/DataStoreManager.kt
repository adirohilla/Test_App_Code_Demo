package com.kv.pribizz.datastoremanager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.kv.pribizz.model.UserModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(@ApplicationContext val context: Context) :
    BaseDataStoreManager(context) {

    companion object {
        val IS_LOGIN = booleanPreferencesKey("IS_LOGIN")
        val USER = stringPreferencesKey("USER")
    }

    suspend fun saveUser(userModel: UserModel?) {
        if (userModel != null) {
            setValue(USER, Gson().toJson(userModel))
            setValue(IS_LOGIN, true)
        } else {
            setValue(USER, "")
            setValue(IS_LOGIN, false)
        }
    }

    fun getUser() = context.dataStore.data.map {
        Gson().fromJson(it[USER], UserModel::class.java)
    }

    fun isLogin() = context.dataStore.data.map {
        it[IS_LOGIN] ?: false
    }
//    val myValue = context.dataStore.data.first()
}