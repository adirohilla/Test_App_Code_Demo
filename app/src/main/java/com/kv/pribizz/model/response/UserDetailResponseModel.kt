package com.kv.pribizz.model.response

import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.model.UserModel

data class UserDetailResponseModel(
    val data: UserModel

) : BaseResponseModel()