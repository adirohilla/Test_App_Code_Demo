package com.kv.pribizz.model

data class InvestmentModel(
    val id: String,
    val user_id: String,
    val plan_id: String,
    val purchased_on: String?,
    val invested_amount: String?,
    val investment_tenure: String?,
    val weekly_return: String?,
    val total_return: String?,
    val total_return_paid: String?,
    val tenure_paid: String?,
    val is_expired: String?,
    val plan_name: String?,
) {
//
//    fun getDate(): String {
//        return DateFormater.formatDate(
//            DateFormater.format_dd_MM_yyyy_hh_mm_a,
//            DateFormater.format_dd_MM_yyyy, created
//        )
//    }

    fun isCredit(): Boolean {
        return false
    }
}