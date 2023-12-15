package com.kv.pribizz.model.response

import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.model.NotificationModel

data class NotificationListResponseModel(
    val data: ArrayList<NotificationModel>

) : BaseResponseModel()