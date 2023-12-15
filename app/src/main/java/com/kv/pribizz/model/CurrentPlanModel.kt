package com.kv.pribizz.model

import android.text.TextUtils
import com.kv.pribizz.utils.DateFormater
import com.kv.pribizz.utils.DateFormater.format_MMM_dd_yyyy_hh_mm_a
import com.kv.pribizz.utils.DateFormater.format_dd_MM_yyyy_hh_mm_a

data class CurrentPlanModel(
    val invested_amount: String?,
    val investment_tenure: String?,
) {
}