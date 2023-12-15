package com.kv.pribizz.model.response

import com.kv.pribizz.model.BalanceModel
import com.kv.pribizz.model.BaseResponseModel

data class UserBalanceResponseModel(
    val data: BalanceModel

) : BaseResponseModel()