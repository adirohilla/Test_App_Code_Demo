package com.kv.pribizz.model.response

import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.model.UserModel
import com.kv.pribizz.model.VersionModel

data class AppVersionResponseModel(
    val data: VersionModel

) : BaseResponseModel()