package com.kv.pribizz.model

open class BaseResponseModel {
    var code: Int = 0
    var message: String? = null
    var error: Boolean = false
}