package com.kv.pribizz.utils

sealed class Validator {
    class Success(val data: String = "") : Validator()
    class Error(val msg: String = "") : Validator()
}
