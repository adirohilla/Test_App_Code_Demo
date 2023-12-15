package com.kv.pribizz.model.response

import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.model.UserModel

data class LoginResponseModel(
    val data: UserModel

) : BaseResponseModel()