package com.kv.pribizz.model

import android.text.TextUtils
import com.kv.pribizz.utils.DateFormater
import com.kv.pribizz.utils.DateFormater.format_MMM_dd_yyy
import com.kv.pribizz.utils.DateFormater.format_MMM_dd_yyyy_hh_mm_a
import com.kv.pribizz.utils.DateFormater.format_dd_MM_yyyy_hh_mm_a

data class WalletModel(
    val id: String?,
    val type: String?,
    val amount: String?,
    val created_at: String?,
) {

    fun getDate(): String? {
        if (TextUtils.isEmpty(created_at)) return created_at
        return DateFormater.formatDate(
            format_dd_MM_yyyy_hh_mm_a,
            format_MMM_dd_yyy,
            created_at
        )
    }

    fun isCredit(): Boolean {
        return type.equals("Credit")
    }
}