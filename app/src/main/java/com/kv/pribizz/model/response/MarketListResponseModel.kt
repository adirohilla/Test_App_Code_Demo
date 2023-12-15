package com.kv.pribizz.model.response

import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.model.MarketModel

data class MarketListResponseModel(
    val data: ArrayList<MarketModel>

) : BaseResponseModel()