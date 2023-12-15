package com.kv.pribizz.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kv.pribizz.data.Repository
import com.kv.pribizz.datastoremanager.DataStoreManager
import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val userModel = dataStoreManager.getUser()
    private val _response_logout: MutableLiveData<NetworkResult<BaseResponseModel>> =
        MutableLiveData()
    val response_logout: LiveData<NetworkResult<BaseResponseModel>> = _response_logout

    init {
    }

    fun logoutUser() = viewModelScope.launch {
        repository.logout().collect {
            _response_logout.value = it
            dataStoreManager.saveUser(null)
        }
    }
}
