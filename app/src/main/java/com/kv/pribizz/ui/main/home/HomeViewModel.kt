package com.kv.pribizz.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kv.pribizz.data.Repository
import com.kv.pribizz.datastoremanager.DataStoreManager
import com.kv.pribizz.model.UserModel
import com.kv.pribizz.model.response.MarqueeResponseModel
import com.kv.pribizz.model.response.UserBalanceResponseModel
import com.kv.pribizz.model.response.UserDetailResponseModel
import com.kv.pribizz.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository, val dataStoreManager: DataStoreManager
) : ViewModel() {
    val _user = MutableLiveData<UserModel?>()

    private val _response_user_balance: MutableLiveData<NetworkResult<UserBalanceResponseModel>> =
        MutableLiveData()
    val response_user_balance: LiveData<NetworkResult<UserBalanceResponseModel>> =
        _response_user_balance
    private val _response_profile: MutableLiveData<NetworkResult<UserDetailResponseModel>> =
        MutableLiveData()
    val response_profile: LiveData<NetworkResult<UserDetailResponseModel>> = _response_profile
    private val _response_marquee: MutableLiveData<NetworkResult<MarqueeResponseModel>> =
        MutableLiveData()
    val response_marquee: LiveData<NetworkResult<MarqueeResponseModel>> = _response_marquee

    init {
        getLocalProfile()
        getMarqueeText()
    }

    private fun getLocalProfile() {
        viewModelScope.launch {
            dataStoreManager.getUser().collect {
                updateObject(it)
            }
        }
    }

    private fun updateObject(it: UserModel?) {
        if (it != null) {
            _user.value = it;
        }
    }


    fun updateuserLocal(userModel: UserModel) {
        viewModelScope.launch {
            dataStoreManager.saveUser(userModel)
        }
    }

    fun getProfile() = viewModelScope.launch {
        _response_profile.value = NetworkResult.Loading()

        repository.getProfile().collect {
            _response_profile.value = it
        }
    }

    fun getBalance() = viewModelScope.launch {
        repository.getUserBalance().collect {
            _response_user_balance.value = it
        }
    }

    private fun getMarqueeText() {
        viewModelScope.launch {
            repository.getMarqueeText().collect { values ->
                _response_marquee.value = values
            }
        }
    }
}