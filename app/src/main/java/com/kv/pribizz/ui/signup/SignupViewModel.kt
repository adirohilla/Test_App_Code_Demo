package com.kv.pribizz.ui.signup

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kv.pribizz.data.Repository
import com.kv.pribizz.model.response.SignupResponseModel
import com.kv.pribizz.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val mobile = MutableLiveData("")
    val name = MutableLiveData("")
    val password = MutableLiveData("")
    val terms_checked = MutableLiveData(true)
    val password1 = MutableLiveData("")
    val referral_code = MutableLiveData("")
    private val _response: MutableLiveData<NetworkResult<SignupResponseModel>> = MutableLiveData()
    val response: LiveData<NetworkResult<SignupResponseModel>> = _response
//    val error_msg = MutableSharedFlow<String>()

    fun onRegisterClick(v: View) {
        viewModelScope.launch {
            val validation = validate()
            if (!validation.isNullOrEmpty()) {
//                error_msg.emit(validation)
                _response.value = NetworkResult.Error(validation)
            } else {
                repository.signup(
                    name.value.toString(),
                    mobile.value.toString(),
                    password.value.toString(),
                    referral_code.value.toString(),
                    "device_token",
                ).collect { values ->
                    _response.value = values
                }
            }
        }
    }

    fun validate(): String {
        if (mobile.value.isNullOrBlank()) {
            return "Please enter mobile number"
        }

        if (mobile.value.toString().length < 10) {
            return "Please enter valid mobile number"
        }

        if (name.value.isNullOrBlank()) {
            return "Please enter name"
        }

        if (password.value.isNullOrBlank()) {
            return "Please enter password"
        }

        if (password.value.toString().length < 6 || password.value.toString().length > 15) {
            return "Password should be 6-15 char"
        }
        if (password1.value.isNullOrBlank()) {
            return "Please enter confirm password"
        }

        if (password1.value.toString().length < 6 || password1.value.toString().length > 15) {
            return "Confirm password should be 6-15 char"
        }
        if (!password1.value.equals(password.value)) {
            return "Confirm password should be same as password"
        }
        if (terms_checked.value == false) {
            return "Please check Terms and condition"
        }
        return ""
    }
}
