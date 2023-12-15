package com.kv.pribizz.model.response

import com.kv.pribizz.model.EarningModel
import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.model.InvestmentModel

data class InvestmentHistoryResponseModel(
    val data: ArrayList<InvestmentModel>

) : BaseResponseModel()