package com.kv.pribizz.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kv.pribizz.data.Repository
import com.kv.pribizz.datastoremanager.DataStoreManager
import com.kv.pribizz.model.response.AppVersionResponseModel
import com.kv.pribizz.model.response.LoginResponseModel
import com.kv.pribizz.model.response.NetworkResponseModel
import com.kv.pribizz.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: Repository,
    val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val _response: MutableLiveData<NetworkResult<AppVersionResponseModel>> = MutableLiveData()
    val response: LiveData<NetworkResult<AppVersionResponseModel>> = _response

    init {
        getAppVersion()
    }


    fun getAppVersion() {
        viewModelScope.launch {
            repository.get_version(

            ).collect { values ->
                _response.value = values
            }
        }
    }
}

//class SplashViewModelFactory() : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return SplashViewModel() as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}