package com.kv.pribizz.model

import com.kv.pribizz.utils.DateFormater

data class BetHistoryModel(
    val market_name: String?,
    val bet_type: String?,
    val selected_number: String?,
    val stack: String?,
    val bet_amount: Int,
    val created: String?,
) {
    fun getDate(): String {
        return DateFormater.formatDate(
            DateFormater.format_dd_MM_yyyy_hh_mm_a,
            DateFormater.format_dd_MM_yyyy, created
        )
    }

    fun getBetAmount(): String {
        return bet_amount.toString()
    }
    /*
    market_name": "GALI",
      "bet_type": "Haruf - Andar",
      "selected_number": "1,3,6",
      "stack": "10.00",
      "bet_amount": "30.00",
      "created": "01-05-2022 08:00 AM"
     */
}