package com.kv.pribizz.model.response

import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.model.ResultChartModel

data class ResultChartResponseModel(
    val data: ResultChartModel

) : BaseResponseModel()