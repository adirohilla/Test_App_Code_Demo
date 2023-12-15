package com.kv.pribizz.model.response

import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.model.PlanModel

data class PlanResponseModel(
    val data: ArrayList<PlanModel>

) : BaseResponseModel()