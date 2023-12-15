package com.kv.pribizz

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.kv.pribizz.model.UserModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    var userModel = MutableLiveData<UserModel>()

    init {
        getUser()
    }

    private fun getUser() {
//        GlobalScope.launch {
//            dataStoreManager.getUser().collect() {
//                withContext(Dispatchers.Main) {
//                    userModel.value = it
//                }
//            }
//        }
    }
}