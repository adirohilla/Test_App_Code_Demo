package com.kv.pribizz.model.response

import com.kv.pribizz.model.EarningModel
import com.kv.pribizz.model.BaseResponseModel

data class ReturnHistoryResponseModel(
    val data: ArrayList<EarningModel>

) : BaseResponseModel()