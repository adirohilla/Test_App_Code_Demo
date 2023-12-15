package com.kv.pribizz.ui.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kv.pribizz.data.Repository
import com.kv.pribizz.datastoremanager.DataStoreManager
import com.kv.pribizz.model.UserModel
import com.kv.pribizz.model.response.LoginResponseModel
import com.kv.pribizz.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStoreManager: DataStoreManager

) : ViewModel() {

    val mobile = MutableLiveData("")
    val password = MutableLiveData("")
    private val _response: MutableLiveData<NetworkResult<LoginResponseModel>> = MutableLiveData()
    val response: LiveData<NetworkResult<LoginResponseModel>> = _response

    fun onLoginClick(v: View) {
        viewModelScope.launch {
            val validation = validate()
            if (!validation.isNullOrEmpty()) {
                _response.value = NetworkResult.Error(validation)
            } else {
                repository.login(
                    mobile.value.toString(),
                    password.value.toString(),
                    "device_token",
                ).collect { values ->
                    _response.value = values
                }
            }
        }
    }

    fun onForgotPasswordClick(v: View) {

    }

    fun validate(): String {

        if (mobile.value.isNullOrBlank()) {
            return "Please enter mobile number"
        }

        if (password.value.isNullOrBlank()) {
            return "Please enter password"
        }

        if (password.value.toString().length < 6 || password.value.toString().length > 15) {
            return "Password should be 6-15 char"
        }

        return ""
    }

    fun userLogin(userModel: UserModel) {
        viewModelScope.launch {
            dataStoreManager.saveUser(userModel)
        }
    }

}

//class LoginViewModelFactory() : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return LoginViewModel() as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}