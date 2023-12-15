package com.kv.pribizz.model.response

import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.model.NetworkMainModel

data class NetworkResponseModel(
    val data: NetworkMainModel

) : BaseResponseModel() {
}