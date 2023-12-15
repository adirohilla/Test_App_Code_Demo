package com.kv.pribizz.datastoremanager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
// Create junit test test cases for this class  in app/src/test/java/com/kv/pribizz/datastoremanager/BaseDataStoreManagerTest.kt
abstract class BaseDataStoreManager(val mcontext: Context) {
    private var USER_DATASTORE = "userprefs"

    protected val Context.dataStore: DataStore<Preferences> by preferencesDataStore(USER_DATASTORE)

    private suspend fun <T> DataStore<Preferences>.getFromLocalStorage(
        PreferencesKey: Preferences.Key<T>, func: T.() -> Unit
    ) {
        data.catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map {
            it[PreferencesKey]
        }.collect {
            it?.let { func.invoke(it as T) }
        }
    }

    protected suspend fun <T> setValue(key: Preferences.Key<T>, value: T) {
        mcontext.dataStore.edit {
            it[key] = value
        }
    }

    protected suspend fun <T> getValue(key: Preferences.Key<T>, responseFunc: T.() -> Unit) {
        mcontext.dataStore.getFromLocalStorage(key) {
            responseFunc.invoke(this)
        }
    }

}