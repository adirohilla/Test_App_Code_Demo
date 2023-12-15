package com.kv.pribizz.model

import android.text.TextUtils
import com.kv.pribizz.utils.DateFormater
import com.kv.pribizz.utils.DateFormater.format_MMM_dd_yyyy_hh_mm_a
import com.kv.pribizz.utils.DateFormater.format_dd_MM_yyyy_hh_mm_a

data class PlanModel(
    val id: String?,
    val amount: String?,
    val created_at: String?,
) {
    lateinit var investment_amount: String
    lateinit var investment_tenure: String
    lateinit var weekly_return: String
    lateinit var total_return: String
    lateinit var plan_name: String
    lateinit var referral_commission: String
    var is_active = "N"

    fun getDate(): String? {
        if (TextUtils.isEmpty(created_at)) return created_at
        return DateFormater.formatDate(
            format_dd_MM_yyyy_hh_mm_a,
            format_MMM_dd_yyyy_hh_mm_a,
            created_at
        )
    }

    fun getText1(): String {
        return "Invest" +
                "\n₹ $investment_amount" +
                "\n&" +
                "\nGET " +
                "\n₹ $total_return"
    }

    fun getTitle(): String {
        return "Get ₹$weekly_return on every week"
    }

    fun getDuration(): String {
        return "Duration = $investment_tenure Week"
    }

    fun getCommission(): String {
        return "Get $referral_commission% commission on every referral on Network"
    }
}