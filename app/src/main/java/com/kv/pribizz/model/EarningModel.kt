package com.kv.pribizz.model

import com.kv.pribizz.utils.DateFormater

data class EarningModel(
    val total_payout: String?,
    val paid_on: Long?,
) {

    fun getDate(): String {
        return DateFormater.formatDate(
            (paid_on?.times(1000)),
            DateFormater.format_dd_MM_yyyy
        )
    }
}
/*
{"id":"14",
"payout_week":"14",
"payout_amount":"50.00",
"commission":"0.00",
"total_payout":"50.00",
"paid_on":"1660727137",
"plan_name":"Test Plan",
"status":"PAID"}
 */