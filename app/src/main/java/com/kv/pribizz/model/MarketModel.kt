package com.kv.pribizz.model

import com.kv.pribizz.utils.DateFormater
import com.kv.pribizz.utils.DateFormater.format_hh_mm_a

data class MarketModel(
    val market_id: String? = null,
    val market_name: String,
    val bet_start_time: Long = 0,
    val result_time: Long = 0,
    val duration: String? = null,
    val draw_time: String? = null,
    val week_days: String? = null,
    val max_bet_amount: String? = null,
    val result_id: String? = null,
    val market_result_andar: String? = null,
    val market_result_bahar: String? = null,
) {
    override fun toString(): String {
        return market_name
    }

    fun getStartTime(): String {
        return DateFormater.formatDate(bet_start_time * 1000, format_hh_mm_a)
    }

    fun getEndTime(): String {
        return DateFormater.formatDate(result_time * 1000, format_hh_mm_a)
    }

    fun canPlaceBet(): Boolean {
        val bet_start = bet_start_time * 1000;
        val result = result_time * 1000;
        return (System.currentTimeMillis() > bet_start && System.currentTimeMillis() < result)
    }

    fun getResult(): String {
        var result = ""
        if (!market_result_andar.isNullOrEmpty()) {
            result = market_result_andar
        } else {
            result = "X"
        }
        if (!market_result_bahar.isNullOrEmpty()) {
            result += market_result_bahar
        } else {
            result += "X"
        }
        return result
    }
    /*
    market_id": "1",
            "market_name": "GALI",
            "bet_start_time": 1650169800,
            "result_time": 1650234600,
            "duration": "64800",
            "draw_time": "20",
            "week_days": "1,2,3,4,5,6,7",
            "max_bet_amount": "50000",
            "result_id": null,
            "market_result_andar": null,
            "market_result_bahar
     */
}